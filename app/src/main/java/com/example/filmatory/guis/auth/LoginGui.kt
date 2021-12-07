package com.example.filmatory.guis.auth

import android.content.Intent
import android.widget.Button
import android.widget.TextView
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.auth.LoginScene
import com.example.filmatory.scenes.activities.auth.RegisterScene
import com.example.filmatory.scenes.activities.auth.ResetPasswordScene
import com.example.filmatory.systems.AuthSystem
import com.google.android.material.textfield.TextInputEditText

/**
 * LoginGui contains all the gui elements for the login page
 *
 * @param loginScene The scene to use
 * @param authSystem The auth system to use
 */
class LoginGui(loginScene: LoginScene, authSystem: AuthSystem) {
    private var regBtn = loginScene.findViewById<TextView>(R.id.regHereBtn)
    private var logBtn = loginScene.findViewById<Button>(R.id.login_btn)
    private var resetBtn = loginScene.findViewById<TextView>(R.id.forgotPasswordBtn)
    private var emailEditTextField = loginScene.findViewById<TextInputEditText>(R.id.loginEmailEditTextField)
    private var passwordEditTextField = loginScene.findViewById<TextInputEditText>(R.id.loginPasswordEditTextField)

    init {
        regBtn.setOnClickListener {
            val intent = Intent(loginScene, RegisterScene::class.java)
            loginScene.finish()
            loginScene.startActivity(intent)
        }
        logBtn.setOnClickListener {
            var email = emailEditTextField.text.toString()
            var password = passwordEditTextField.text.toString()
            authSystem.loginUser( email, password);
        }
        resetBtn.setOnClickListener{
            val intent = Intent(loginScene, ResetPasswordScene::class.java)
            loginScene.finish()
            loginScene.startActivity(intent)
        }
    }
}