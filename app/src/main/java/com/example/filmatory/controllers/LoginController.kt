package com.example.filmatory.controllers

import android.content.Intent
import android.widget.TextView
import com.example.filmatory.R
import com.example.filmatory.scenes.LoginScene
import com.example.filmatory.scenes.RegisterScene

class LoginController(loginScene: LoginScene) : MainController(loginScene) {
    val loginScene = loginScene
    private var regBtn: TextView

    //Backend


    //Frontend


    //Init
    init {
        regBtn = loginScene.findViewById(R.id.regHereBtn)
        regBtn.setOnClickListener {
            val intent = Intent(loginScene, RegisterScene::class.java)
            loginScene.startActivity(intent)
        }
        apiSystem.postUser("hello123@protonmail.com", "Test1234!", "Test1234!", ::printTest)
        //apiSystem.requestApprovedReviewById("60b3a9194001540015069d2c", ::printTest)
    }

    //Just a test function for API
    fun printTest(any: Any) {
        println(any)
    }
}