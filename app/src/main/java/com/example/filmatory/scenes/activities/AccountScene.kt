package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.AccountController
import com.example.filmatory.scenes.SuperScene

/**
 * AccountScene contains all the components for creating an account page
 *
 */
class AccountScene : SuperScene() {
    private lateinit var accountController: AccountController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        accountController = AccountController(this)
    }
}

