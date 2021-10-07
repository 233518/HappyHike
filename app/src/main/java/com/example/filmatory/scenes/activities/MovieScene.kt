package com.example.filmatory.scenes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.MovieController
import com.example.filmatory.scenes.SuperScene

class MovieScene : SuperScene() {
    private lateinit var movieController : MovieController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_scene)
        movieController = MovieController(this)
    }
}