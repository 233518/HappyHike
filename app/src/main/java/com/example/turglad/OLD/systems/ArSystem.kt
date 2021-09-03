package com.example.turglad.OLD.systems

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.turglad.MainActivity
import com.example.turglad.OLD.arhelpers.CameraPermissionHelper
import com.example.turglad.OLD.ars.*
import com.google.ar.core.Config

class ArSystem(mainActivity: MainActivity, context: Context) {
    //Main activity
    var mainActivity = mainActivity;

    //Context
    var context = context;

    //Check compatability
    var arCompatability = ArCompatability(context);

    //ArCoreVerifier
    var ArCoreVerify = ArCoreVerify(context, mainActivity);

    //Permissions
    lateinit var arPermission: ArPermission;

    //Session
    lateinit var session: ArSession;

    //Looper handler
    private var backgroundHandler: Handler = Handler(Looper.getMainLooper())

    //Camera stuff
    lateinit var arCameraDevice: ArCameraDevice;

    init {
        println("Initializing ArSystem...")
    }

    fun openCamera() {
        arCameraDevice = ArCameraDevice(session);
        // Wrap the callback in a shared camera callback.
        val wrappedCallback = session.sharedCamera.createARDeviceStateCallback(arCameraDevice, backgroundHandler)

        // Store a reference to the camera system service.
        val cameraManager = mainActivity.getSystemService(Context.CAMERA_SERVICE) as CameraManager

        // Open the camera device using the ARCore wrapped callback.
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        println("OPEN CAMERA")
        cameraManager.openCamera(session.cameraId, wrappedCallback, backgroundHandler)
    }

    fun hasCameraPermissionResult(context : Context, activity : MainActivity) {
        println("Checking hasCameraPermission RESULT...")
        //Check camera permissions
        if (!CameraPermissionHelper.hasCameraPermission(activity)) {
            Toast.makeText(context, "Camera permission is needed to run this application", Toast.LENGTH_LONG)
                .show()
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(activity)) {
                // Permission denied with checking "Do not ask again".
                CameraPermissionHelper.launchPermissionSettings(activity)
            }
        }
    }

    fun checkCameraPermission() {
        println("Checking camera permission...")
        arPermission = ArPermission(mainActivity);
    }

    fun verifyArCore() {
        println("verifying if ArCore is supported and ready")
        if(ArCoreVerify.isARCoreSupportedAndUpToDate()) {
            createSession();
        }
    }

    fun createSession() {
        // Create a new ARCore session.
        session = ArSession(context)

        // Create a session config.
        val config = Config(session.mSession)

        // Do feature-specific operations here, such as enabling depth or turning on
        // support for Augmented Faces.

        // Configure the session.
        session.mSession.configure(config);

        //Configure camera
        session.configureCamera();
    }
}