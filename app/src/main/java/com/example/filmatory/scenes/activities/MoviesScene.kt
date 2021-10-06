package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.MoviesController
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.utils.BlurImage

class MoviesScene : SuperScene() {
    private lateinit var moviesController: MoviesController
    private var blurImage: BlurImage = BlurImage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        moviesController = MoviesController(this)
        blurImage.blurImage(this, R.drawable.image9, findViewById(R.id.mediaImgView))
    }
}