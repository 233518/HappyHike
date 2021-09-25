package com.example.filmatory.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.RegisterController

class RegisterScene : AppCompatActivity() {
    private lateinit var registerController: RegisterController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)
        registerController = RegisterController(this)
    }
}