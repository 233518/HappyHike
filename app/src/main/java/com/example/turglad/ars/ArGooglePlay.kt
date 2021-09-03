package com.example.turglad.ars

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.example.turglad.MainActivity
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Session
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException

class ArGooglePlay(context : Context, activity : MainActivity) {

    var mUserRequestedInstall = true
    var mSession: Session? = null;

    init {
        try {
            if (mSession == null) {
                when (ArCoreApk.getInstance().requestInstall(activity, mUserRequestedInstall)) {
                    ArCoreApk.InstallStatus.INSTALLED -> {
                        // Success: Safe to create the AR session.
                        mSession = Session(context)
                    }
                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                        // When this method returns `INSTALL_REQUESTED`:
                        // 1. ARCore pauses this activity.
                        // 2. ARCore prompts the user to install or update Google Play
                        //    Services for AR (market://details?id=com.google.ar.core).
                        // 3. ARCore downloads the latest device profile data.
                        // 4. ARCore resumes this activity. The next invocation of
                        //    requestInstall() will either return `INSTALLED` or throw an
                        //    exception if the installation or update did not succeed.
                        mUserRequestedInstall = false
                    }
                }
            }
        } catch (e: UnavailableUserDeclinedInstallationException) {
            // Display an appropriate message to the user and return gracefully.
            Toast.makeText(context, "TODO: handle exception " + e, Toast.LENGTH_LONG)
                .show()
        }
    }

}