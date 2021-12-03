package com.example.filmatory.controllers.sceneControllers.auth

import com.example.filmatory.guis.auth.RegisterGui
import com.example.filmatory.scenes.activities.auth.RegisterScene

/**
 * RegisterController manipulates the RegisterScene gui
 *
 * @property registerScene The RegisterScene to use
 */
class RegisterController(private val registerScene: RegisterScene) : AuthController(registerScene) {
    private var registerGui = RegisterGui(registerScene, authSystem)
}