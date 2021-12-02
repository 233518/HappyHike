package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.anychart.APIlib
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.AccountInfoController
import com.example.filmatory.scenes.SuperScene

/**
 * AccountInfoScene is the scene for showing the account info?
 *
 */
class AccountInfoScene : SuperScene() {
    private lateinit var accountInfoController: AccountInfoController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_information)
    }

    override fun onStart() {
        super.onStart()
        accountInfoController = AccountInfoController(this)
    }
}
