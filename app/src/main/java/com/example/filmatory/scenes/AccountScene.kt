package com.example.filmatory.scenes

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.AccountController

import com.example.filmatory.utils.BlurImage

class AccountScene : SuperScene() {
    private lateinit var accountController: AccountController
    private var blurImage: BlurImage = BlurImage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        accountController = AccountController(this)
        blurImage.blurImage(this, R.drawable.image7, findViewById(R.id.account_imgView))

    }
}

