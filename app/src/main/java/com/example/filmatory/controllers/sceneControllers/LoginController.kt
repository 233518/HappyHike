package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.widget.Button
import android.widget.TextView
import com.example.filmatory.R
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.LoginScene
import com.example.filmatory.scenes.activities.RegisterScene
import com.example.filmatory.systems.AuthSystem
import com.google.android.material.textfield.TextInputEditText

class LoginController(private val loginScene: LoginScene) : MainController(loginScene) {
    private var regBtn = loginScene.findViewById<TextView>(R.id.regHereBtn)
    private var logBtn = loginScene.findViewById<Button>(R.id.login_btn)
    private var authSystem = AuthSystem(apiSystem, loginScene.auth, loginScene)

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
    }
}