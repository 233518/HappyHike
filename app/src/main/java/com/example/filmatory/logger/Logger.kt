package com.example.filmatory.logger

import android.os.Build
import android.util.Log
import com.example.filmatory.BuildConfig
import java.time.LocalDateTime

open class Logger {
    companion object {
        open fun debug(message: String) {
            if (BuildConfig.DEBUG) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.d("Debug","[DEBUG][" + LocalDateTime.now() + "]: " + message)
                }
            }
        }
        open fun error(message: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.d("Error","[ERROR][" + LocalDateTime.now() + "]: " + message)
            }
        }

        open fun warning(message: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.d("WARNING","[WARNING][" + LocalDateTime.now() + "]: " + message)
            }
        }

        open fun info(message: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.d("INFO","[INFO][" + LocalDateTime.now() + "]: " + message)
            }
        }
    }
}