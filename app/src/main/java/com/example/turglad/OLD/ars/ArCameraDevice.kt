package com.example.turglad.OLD.ars

import android.content.ContentValues.TAG
import android.hardware.camera2.*
import android.media.ImageReader
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Size
import android.view.Surface
import com.google.ar.core.ImageFormat
import java.util.concurrent.atomic.AtomicBoolean

class ArCameraDevice(session : ArSession) : CameraDevice.StateCallback() {
    var session = session;
    var arMode = false;
    var arcoreActive = false;
    var shouldUpdateSurfaceTexture: AtomicBoolean = AtomicBoolean(false)

    lateinit var cameraDevice: CameraDevice;
    lateinit var previewCaptureRequestBuilder:CaptureRequest.Builder
    lateinit var cameraCaptureSession: CameraCaptureSession;

    var cpuImageSize: Size = session.mSession.cameraConfig.imageSize;

    var cpuImageReader: ImageReader = ImageReader.newInstance(cpuImageSize.width, cpuImageSize.height,ImageFormat.YUV_420_888, 2)

    //Looper handler
    private var backgroundHandler: Handler = Handler(Looper.getMainLooper())

    override fun onOpened(p0: CameraDevice) {
        Log.d(TAG, "Camera device ID " + p0.id + " opened.")
        this.cameraDevice = p0
        createCameraPreviewSession()
    }

    override fun onDisconnected(p0: CameraDevice) {
        TODO("Not yet implemented")
    }

    override fun onError(p0: CameraDevice, p1: Int) {
        TODO("Not yet implemented")
    }

    val cameraSessionStateCallback = object : CameraCaptureSession.StateCallback() {
        // Called when ARCore first configures the camera capture session after
        // initializing the app, and again each time the activity resumes.
        override fun onConfigured(session: CameraCaptureSession) {
            cameraCaptureSession = session
            setRepeatingCaptureRequest()
        }

        override fun onConfigureFailed(p0: CameraCaptureSession) {
            TODO("Not yet implemented")
        }

        override fun onActive(session: CameraCaptureSession) {
            if (arMode && !arcoreActive) {
                resumeARCore()
            }
        }
    }

    val cameraCaptureCallback = object : CameraCaptureSession.CaptureCallback() {
        override fun onCaptureCompleted(
            session: CameraCaptureSession,
            request: CaptureRequest,
            result: TotalCaptureResult
        ) {
            shouldUpdateSurfaceTexture.set(true);
        }
    }

    fun setRepeatingCaptureRequest() {
        cameraCaptureSession.setRepeatingRequest(
            previewCaptureRequestBuilder.build(), cameraCaptureCallback, backgroundHandler
        )
    }

    fun resumeARCore() {
        // Resume ARCore.
        session.mSession.resume()
        arcoreActive = true

        // Set the capture session callback while in AR mode.
        session.sharedCamera.setCaptureCallback(cameraCaptureCallback, backgroundHandler)
    }

    fun createCameraPreviewSession() {
        try {
            // Create an ARCore-compatible capture request using `TEMPLATE_RECORD`.
            previewCaptureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD)

            // Build a list of surfaces, starting with ARCore provided surfaces.
            val surfaceList: MutableList<Surface> = session.mSession.sharedCamera.arCoreSurfaces

            // (Optional) Add a CPU image reader surface.
            surfaceList.add(cpuImageReader.getSurface())

            // The list should now contain three surfaces:
            // 0. sharedCamera.getSurfaceTexture()
            // 1. …
            // 2. cpuImageReader.getSurface()

            // Add ARCore surfaces and CPU image surface targets.
            for (surface in surfaceList) {
                previewCaptureRequestBuilder.addTarget(surface)
            }

            // Wrap the callback in a shared camera callback.
            val wrappedCallback = session.mSession.sharedCamera.createARSessionStateCallback(cameraSessionStateCallback, backgroundHandler)

            // Create a camera capture session for camera preview using an ARCore wrapped callback.
            cameraDevice.createCaptureSession(surfaceList, wrappedCallback, backgroundHandler)
        } catch (e: CameraAccessException) {
            Log.e(TAG, "CameraAccessException", e)
        }
    }

}