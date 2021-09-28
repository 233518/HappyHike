package com.example.filmatory.controllers

import com.example.filmatory.scenes.AccountScene
import com.example.filmatory.scenes.TvScene

class AccountController(accountScene: AccountScene) : MainController(accountScene) {
    val accountScene = accountScene
}