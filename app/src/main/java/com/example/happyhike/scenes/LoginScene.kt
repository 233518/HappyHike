package com.example.happyhike.scenes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.happyhike.R
import com.example.happyhike.controllers.LoginController



class LoginScene : AppCompatActivity() {
    lateinit var loginController: LoginController
    lateinit var registerBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        loginController = LoginController(this)

        registerBtn = findViewById<Button>(R.id.registerBtn)
        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterScene::class.java)
            startActivity(intent)
        }

    }
}