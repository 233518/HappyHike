package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.StartController
import com.example.filmatory.scenes.SuperScene

/**
 * StartScene contains all the components for creating start page
 *
 */
class StartScene : SuperScene() {
    private lateinit var startController: StartController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        startController = StartController(this)
    }
}