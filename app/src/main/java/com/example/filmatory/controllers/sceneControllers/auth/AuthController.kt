package com.example.filmatory.controllers.sceneControllers.auth

import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.auth.AuthScene
import com.example.filmatory.systems.AuthSystem

open class AuthController(private val authScene: AuthScene) : MainController(authScene) {
    protected var authSystem = AuthSystem(apiSystem, authScene.auth, authScene)
}