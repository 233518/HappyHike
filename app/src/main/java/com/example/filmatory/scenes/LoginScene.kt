package com.example.filmatory.scenes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.LoginController



class LoginScene : AppCompatActivity() {
    lateinit var loginController: LoginController
    lateinit var registerBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        //loginController = LoginController(this)

    }
}