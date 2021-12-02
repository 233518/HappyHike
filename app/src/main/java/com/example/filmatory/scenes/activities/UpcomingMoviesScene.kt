package com.example.filmatory.scenes.activities


import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.UpcomingMoviesController
import com.example.filmatory.scenes.SuperScene

/**
 * UpcomingMoviesScene is the scene for showing upcoming movies
 *
 */
class UpcomingMoviesScene : SuperScene() {
    private lateinit var upcomingMoviesController: UpcomingMoviesController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        upcomingMoviesController = UpcomingMoviesController(this)
    }
}