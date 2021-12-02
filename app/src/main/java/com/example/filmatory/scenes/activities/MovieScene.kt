package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.MovieController
import com.example.filmatory.scenes.SuperScene

/**
 * MovieScene is the scene for showing movie information
 *
 */
class MovieScene : SuperScene() {
    private lateinit var movieController : MovieController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_scene)
        movieController = MovieController(this)
    }
}