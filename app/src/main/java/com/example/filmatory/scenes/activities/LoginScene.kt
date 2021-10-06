package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.LoginController
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.utils.BlurImage

class LoginScene : SuperScene() {
    private lateinit var loginController: LoginController
    private var blurImage: BlurImage = BlurImage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        loginController = LoginController(this)
        blurImage.blurImage(this, R.drawable.image3, findViewById(R.id.loginImgView))
    }
}

