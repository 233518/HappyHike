package com.example.filmatory.scenes.activities

import android.content.Intent
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.AccountController
import com.example.filmatory.scenes.SuperScene

/**
 * AccountScene is the scene for showing the account dashboard
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

