package com.example.happyhike.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.happyhike.R
import com.example.happyhike.controllers.LoginController



class LoginScene : AppCompatActivity() {

    lateinit var loginController: LoginController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        loginController = LoginController(this)
    }
}