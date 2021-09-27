package com.example.filmatory.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.StartController
import com.example.filmatory.utils.BlurImage

class StartScene : AppCompatActivity() {
    private lateinit var startController: StartController
    private var blurImage: BlurImage = BlurImage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        startController = StartController(this)
        blurImage.blurImage(this, R.drawable.background_image_startscreen, findViewById(R.id.startscreen_imgView))
    }

}