package com.example.turglad.ars

import android.content.Context
import android.os.Handler
import com.google.ar.core.ArCoreApk

class ArCompatability(context : Context) {

    init {
        maybeEnableArButton(context);
    }

    fun maybeEnableArButton(context : Context) {
        val availability = ArCoreApk.getInstance().checkAvailability(context);
        if (availability.isTransient) {
            // Continue to query availability at 5Hz while compatibility is checked in the background.
            Handler().postDelayed({
                maybeEnableArButton(context)
            }, 200)
        }
        if (availability.isSupported) {
            println("Hi")
        } else { // The device is unsupported or unknown.
            println("Denne funker ikke")
        }
    }
}

