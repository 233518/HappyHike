package com.example.filmatory.controllers.sceneControllers

import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.AccountInfoScene


class AccountInfoController(accountInfoScene: AccountInfoScene) : MainController(accountInfoScene) {
    val accountInfoScene = accountInfoScene
}