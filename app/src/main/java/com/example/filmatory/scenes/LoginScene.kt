package com.example.filmatory.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.LoginController
import com.example.filmatory.utils.BlurImage

class LoginScene : AppCompatActivity() {
    private lateinit var loginController: LoginController
    private var blurImage: BlurImage = BlurImage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        loginController = LoginController(this)
        blurImage.blurImage(this, R.drawable.image3, findViewById(R.id.loginImgView))
    }

}

