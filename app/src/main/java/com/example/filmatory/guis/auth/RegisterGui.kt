package com.example.filmatory.guis.auth

import android.widget.Button
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.auth.RegisterScene
import com.example.filmatory.systems.AuthSystem
import com.google.android.material.textfield.TextInputEditText

class RegisterGui(registerScene: RegisterScene, authSystem: AuthSystem) {
    private var registerBtn = registerScene.findViewById<Button>(R.id.reg_btn)
    private var emailEditField = registerScene.findViewById<TextInputEditText>(R.id.regEmailEditField)
    private var passwordEditField = registerScene.findViewById<TextInputEditText>(R.id.regPasswordEditField)
    private var passwordRepeatEditField = registerScene.findViewById<TextInputEditText>(R.id.regPasswordRepeatEditField)

    init {
        registerBtn.setOnClickListener {
            var email = emailEditField.text.toString()
            var password = passwordEditField.text.toString()
            var passwordRepeat = passwordRepeatEditField.text.toString()
            if(password == passwordRepeat)
                authSystem.registerUser(email, password)
        }
    }

}