package com.example.turglad.OLD.ars

import com.google.ar.core.CameraConfig
import com.google.ar.core.CameraConfigFilter
import java.util.*

class ArCameraConfig(session: ArSession) : CameraConfig() {
    val filter = CameraConfigFilter(session.mSession)

    init {
        filter.targetFps = EnumSet.of(CameraConfig.TargetFps.TARGET_FPS_30);
    }
}