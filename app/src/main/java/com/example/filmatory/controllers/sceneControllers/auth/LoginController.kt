package com.example.filmatory.controllers.sceneControllers.auth

import android.content.Intent
import android.widget.Button
import android.widget.TextView
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.auth.LoginScene
import com.example.filmatory.scenes.activities.auth.RegisterScene
import com.example.filmatory.scenes.activities.auth.ResetPasswordScene
import com.google.android.material.textfield.TextInputEditText

/**
 * LoginController manipulates the LoginScene gui
 *
 * @property loginScene The LoginScene to use
 */
class LoginController(private val loginScene: LoginScene) : AuthController(loginScene) {
    private var regBtn = loginScene.findViewById<TextView>(R.id.regHereBtn)
    private var logBtn = loginScene.findViewById<Button>(R.id.login_btn)
    private var resetBtn = loginScene.findViewById<TextView>(R.id.forgotPasswordBtn)

    init {
        regBtn.setOnClickListener {
            val intent = Intent(loginScene, RegisterScene::class.java)
            loginScene.startActivity(intent)
        }
        logBtn.setOnClickListener {
            var email = loginScene.findViewById<TextInputEditText>(R.id.loginEmailEditTextField).text.toString()
            var password = loginScene.findViewById<TextInputEditText>(R.id.loginPasswordEditTextField).text.toString()
            authSystem.loginUser( email, password);
        }
        resetBtn.setOnClickListener{
            val intent = Intent(loginScene, ResetPasswordScene::class.java)
            loginScene.startActivity(intent)
        }
    }
}