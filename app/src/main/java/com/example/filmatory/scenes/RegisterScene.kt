package com.example.filmatory.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.RegisterController
import com.example.filmatory.utils.BlurImage


class RegisterScene : SuperScene() {
    private lateinit var registerController: RegisterController
    private var blurImage: BlurImage = BlurImage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)
        registerController = RegisterController(this)
        blurImage.blurImage(this, R.drawable.image1, findViewById(R.id.regImgView))
    }
}