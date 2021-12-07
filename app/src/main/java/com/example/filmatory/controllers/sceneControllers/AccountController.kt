package com.example.filmatory.controllers.sceneControllers

import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.AccountGui
import com.example.filmatory.scenes.activities.AccountScene

/**
 * AccountController controls everything related to the account page
 *
 * @property accountScene The AccountScene to use
 */
class AccountController(private val accountScene: AccountScene) : MainController(accountScene) {
    private val accountGui = AccountGui(accountScene, this)
}