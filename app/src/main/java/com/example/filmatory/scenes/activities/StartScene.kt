package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.StartController
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.utils.BlurImage

class StartScene : SuperScene() {
    private lateinit var startController: StartController
    private var blurImage: BlurImage = BlurImage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        startController = StartController(this)
        blurImage.blurImage(this, R.drawable.background_image_startscreen, findViewById(R.id.startscreen_imgView))
    }

}