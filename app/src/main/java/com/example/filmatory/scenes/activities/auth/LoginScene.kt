package com.example.filmatory.scenes.activities.auth

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.auth.LoginController

/**
 * LoginScene contains all the components for creating the login page
 *
 */
class LoginScene : AuthScene() {
    private lateinit var loginController: LoginController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        loginController = LoginController(this)
    }
}

