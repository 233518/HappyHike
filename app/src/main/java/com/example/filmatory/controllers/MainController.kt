package com.example.filmatory.controllers

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.ConfigurationCompat
import com.example.filmatory.R
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.ApiSystem
import com.example.filmatory.systems.SnackbarSystem
import com.yariksoffice.lingver.Lingver

/**
 * MainController manipulates the scene gui
 * Every controller will extend this class
 *
 * @constructor
 *
 * @param scene The scene the controller will be connected to
 */
open class MainController(scene : AppCompatActivity) {
    protected val navSystem = NavSystem(scene)
    protected val apiSystem = ApiSystem()
    protected val snackbarSystem = SnackbarSystem(scene.findViewById(R.id.snackbar_layout))
    protected val languageCode: String = Lingver.getInstance().getLanguage()
}