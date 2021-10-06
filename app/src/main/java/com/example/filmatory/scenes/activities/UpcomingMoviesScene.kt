package com.example.filmatory.scenes.activities


import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.UpcomingMoviesController
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.utils.BlurImage


class UpcomingMoviesScene : SuperScene() {
    private lateinit var upcomingMoviesController: UpcomingMoviesController
    private var blurImage: BlurImage = BlurImage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        upcomingMoviesController = UpcomingMoviesController(this)
        blurImage.blurImage(this, R.drawable.image9, findViewById(R.id.mediaImgView))
    }
}