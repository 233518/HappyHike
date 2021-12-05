package com.example.filmatory.controllers.sceneControllers.auth

import com.example.filmatory.guis.auth.LoginGui
import com.example.filmatory.scenes.activities.auth.LoginScene

/**
 * LoginController manipulates the LoginScene gui
 *
 * @property loginScene The LoginScene to use
 */
class LoginController(private val loginScene: LoginScene) : AuthController(loginScene) {
    private var loginGui = LoginGui(loginScene, authSystem)
}