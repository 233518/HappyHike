package com.example.filmatory.controllers.sceneControllers.auth

import android.widget.Button
import com.example.filmatory.R
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.auth.RegisterScene
import com.example.filmatory.systems.AuthSystem
import com.google.android.material.textfield.TextInputEditText

class RegisterController(private val registerScene: RegisterScene) : AuthController(registerScene) {
    private val registerBtn = registerScene.findViewById<Button>(R.id.reg_btn)

    init {
        registerBtn.setOnClickListener {
            var email = registerScene.findViewById<TextInputEditText>(R.id.regEmailEditField).text.toString()
            var password = registerScene.findViewById<TextInputEditText>(R.id.regPasswordEditField).text.toString()
            var passwordRepeat = registerScene.findViewById<TextInputEditText>(R.id.regPasswordRepeatEditField).text.toString()
            if(password == passwordRepeat)
                authSystem.registerUser(email, password)
        }
    }
}