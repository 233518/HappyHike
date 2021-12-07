package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.AccountInfoController
import com.example.filmatory.scenes.SuperScene

/**
 * AccountInfoScene contains all the components for creating a account info page
 *
 */
class AccountInfoScene : SuperScene() {
    private lateinit var accountInfoController: AccountInfoController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_information)
        accountInfoController = AccountInfoController(this)
    }
}