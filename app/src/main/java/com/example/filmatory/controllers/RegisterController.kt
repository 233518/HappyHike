package com.example.filmatory.controllers

import com.example.filmatory.guis.RegisterGui
import com.example.filmatory.scenes.RegisterScene
import com.example.filmatory.systems.login.RegisterSystem

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
