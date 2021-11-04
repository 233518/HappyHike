package com.example.filmatory.controllers.sceneControllers.auth

import android.widget.Button
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.auth.RegisterScene
import com.example.filmatory.scenes.activities.auth.ResetPasswordScene
import com.google.android.material.textfield.TextInputEditText

/**
 * RegisterController manipulates the RegisterScene gui
 *
 * @property registerScene The RegisterScene to use
 */
class ResetPasswordController(private val resetPasswordScene: ResetPasswordScene) : AuthController(resetPasswordScene) {
    private val resetPasswordBtn = resetPasswordScene.findViewById<Button>(R.id.reset_password_btn)

    init {
        resetPasswordBtn.setOnClickListener {
            val email = resetPasswordScene.findViewById<TextInputEditText>(R.id.resetPasswordEmailTextField).text.toString()
            authSystem.sendResetLink(email)
        }
    }
}