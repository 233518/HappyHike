package com.example.turglad.systems

import android.content.Context
import android.widget.Toast
import com.example.turglad.MainActivity
import com.example.turglad.arhelpers.CameraPermissionHelper
import com.example.turglad.ars.ArCompatability
import com.example.turglad.ars.ArGooglePlay
import com.example.turglad.ars.ArPermission

class ArSystem(mainActivity: MainActivity, context: Context) {
    //Check compatability
    var arCompatability = ArCompatability(context);

    //Main activity
    var mainActivity = mainActivity;

    //Context
    var context = context;

    init {
        println("Initializing ArSystem...")
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
        var arPermission = ArPermission(mainActivity);
    }

    fun checkGooglePLay() {
        println("Checking google play...")
        var arGooglePlay = ArGooglePlay(context, mainActivity);
    }
}