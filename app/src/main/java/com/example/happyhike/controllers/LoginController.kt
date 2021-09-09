package com.example.happyhike.controllers

import android.widget.Button
import com.example.happyhike.guis.LoginGui
import com.example.happyhike.scenes.LoginScene
import com.example.happyhike.systems.login.LoginSystem
import com.example.happyhike.R

class LoginController(loginScene: LoginScene) {
    private var loginGui: LoginGui = LoginGui()
    lateinit var loginSystem: LoginSystem

    //Init now
    var loginScene = loginScene;
    init {
        loginGui = LoginGui()
        loginSystem = LoginSystem(loginScene)
    }
}