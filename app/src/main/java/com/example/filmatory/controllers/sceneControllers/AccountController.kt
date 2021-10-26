package com.example.filmatory.controllers.sceneControllers

import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.AccountScene

/**
 * AccountController manipulates the AccountScene gui
 *
 * @property accountScene The AccountScene to use
 */
class AccountController(private val accountScene: AccountScene) : MainController(accountScene) {
}