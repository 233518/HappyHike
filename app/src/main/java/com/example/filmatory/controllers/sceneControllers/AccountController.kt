package com.example.filmatory.controllers.sceneControllers

import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.AccountScene

class AccountController(accountScene: AccountScene) : MainController(accountScene) {
    val accountScene = accountScene
}