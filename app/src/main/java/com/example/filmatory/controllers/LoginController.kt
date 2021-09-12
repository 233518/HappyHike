package com.example.filmatory.controllers

import com.example.filmatory.guis.LoginGui
import com.example.filmatory.scenes.LoginScene
import com.example.filmatory.systems.login.LoginSystem

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