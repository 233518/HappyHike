package com.example.filmatory.controllers

import com.example.filmatory.guis.LoginGui
import com.example.filmatory.scenes.LoginScene

class LoginController(loginScene: LoginScene) {
    private var loginGui: LoginGui = LoginGui()

    //Init now
    var loginScene = loginScene;
    init {
        loginGui = LoginGui()
    }
}