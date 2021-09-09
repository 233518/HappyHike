package com.example.happyhike.controllers

import com.example.happyhike.guis.LoginGui
import com.example.happyhike.guis.RegisterGui
import com.example.happyhike.scenes.LoginScene
import com.example.happyhike.scenes.RegisterScene
import com.example.happyhike.systems.login.LoginSystem
import com.example.happyhike.systems.login.RegisterSystem

class RegisterController(registerScene: RegisterScene) {
    private var registerGui: RegisterGui = RegisterGui()
    lateinit var registerSystem: RegisterSystem

    //Init now
    var registerScene = registerScene;
    init {
        registerGui = RegisterGui()
        registerSystem = RegisterSystem(registerScene)
    }
}
