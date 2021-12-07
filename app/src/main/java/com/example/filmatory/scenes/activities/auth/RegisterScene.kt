package com.example.filmatory.scenes.activities.auth

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.auth.RegisterController

/**
 * RegisterScene contains all the components for creating register page
 *
 */
class RegisterScene : AuthScene() {
    private lateinit var registerController: RegisterController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)
        registerController = RegisterController(this)
    }
}