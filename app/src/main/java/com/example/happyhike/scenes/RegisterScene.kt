package com.example.happyhike.scenes

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.happyhike.R
import com.example.happyhike.controllers.LoginController
import com.example.happyhike.controllers.RegisterController

class RegisterScene : AppCompatActivity() {
    lateinit var registerController: RegisterController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)
        registerController = RegisterController(this)
    }
}