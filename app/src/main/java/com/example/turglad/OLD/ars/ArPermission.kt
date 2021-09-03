package com.example.turglad.OLD.ars

import com.example.turglad.MainActivity
import com.example.turglad.OLD.arhelpers.CameraPermissionHelper

class ArPermission(mainActivity: MainActivity) {
    init {
        // ARCore requires camera permission to operate.
        if (!CameraPermissionHelper.hasCameraPermission(mainActivity)) {
            CameraPermissionHelper.requestCameraPermission(mainActivity)
        }
    }
}