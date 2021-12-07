package com.example.filmatory.guis.auth

import android.widget.Button
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.auth.ResetPasswordScene
import com.example.filmatory.systems.AuthSystem
import com.google.android.material.textfield.TextInputEditText

/**
 * ResetGui contains all the gui elements for the reset page
 *
 * @param resetPasswordScene The scene to use
 * @param authSystem The auth system to use
 */
class ResetGui(resetPasswordScene: ResetPasswordScene, authSystem: AuthSystem) {
    private var resetPasswordBtn = resetPasswordScene.findViewById<Button>(R.id.reset_password_btn)
    private var emailEditTextField = resetPasswordScene.findViewById<TextInputEditText>(R.id.resetPasswordEmailTextField)

    init {
        resetPasswordBtn.setOnClickListener {
            val email = emailEditTextField.text.toString()
            authSystem.sendResetLink(email)
        }
    }
}