package com.example.turglad.ars

import com.example.turglad.MainActivity
import com.example.turglad.arhelpers.CameraPermissionHelper

class ArPermission(mainActivity: MainActivity) {

    init {
        // ARCore requires camera permission to operate.
        if (!CameraPermissionHelper.hasCameraPermission(mainActivity)) {
            CameraPermissionHelper.requestCameraPermission(mainActivity)
        }
    }
}