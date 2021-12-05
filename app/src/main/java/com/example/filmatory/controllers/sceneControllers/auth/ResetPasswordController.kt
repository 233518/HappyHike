package com.example.filmatory.controllers.sceneControllers.auth

import com.example.filmatory.guis.auth.ResetGui
import com.example.filmatory.scenes.activities.auth.ResetPasswordScene

/**
 * RegisterController manipulates the RegisterScene gui
 *
 * @property registerScene The RegisterScene to use
 */
class ResetPasswordController(private val resetPasswordScene: ResetPasswordScene) : AuthController(resetPasswordScene) {
    private val resetGui = ResetGui(resetPasswordScene, authSystem)
}