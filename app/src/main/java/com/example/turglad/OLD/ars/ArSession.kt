package com.example.turglad.OLD.ars
import android.content.Context
import com.google.ar.core.CameraConfig
import com.google.ar.core.CameraConfigFilter
import com.google.ar.core.Session
import com.google.ar.core.SharedCamera
import java.util.*

class ArSession(context: Context) {
    var mSession: Session = Session(context, EnumSet.of(Session.Feature.SHARED_CAMERA))
        get() = field;
    lateinit var sharedCamera: SharedCamera;
    lateinit var cameraId: String

    fun configureCamera() {
        // Create a camera config filter for the session.
        val filter = CameraConfigFilter(mSession)

        mSession.cameraConfig.imageSize

        // Return only camera configs that target 30 fps camera capture frame rate.
        filter.targetFps = EnumSet.of(CameraConfig.TargetFps.TARGET_FPS_30)

        // Return only camera configs that will not use the depth sensor.
        filter.depthSensorUsage = EnumSet.of(CameraConfig.DepthSensorUsage.DO_NOT_USE)

        // Get list of configs that match filter settings.
        // In this case, this list is guaranteed to contain at least one element,
        // because both TargetFps.TARGET_FPS_30 and DepthSensorUsage.DO_NOT_USE
        // are supported on all ARCore supported devices.
        val cameraConfigList = mSession.getSupportedCameraConfigs(filter)

        // Use element 0 from the list of returned camera configs. This is because
        // it contains the camera config that best matches the specified filter
        // settings.
        mSession.cameraConfig = cameraConfigList[0]
        configureSharedSession();
    }

    fun configureSharedSession() {
        // Store the ARCore shared camera reference.
        sharedCamera = mSession.sharedCamera

        // Store the ID of the camera that ARCore uses.
        cameraId = mSession.cameraConfig.cameraId
    }
}

