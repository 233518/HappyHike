package com.example.filmatory.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.MovieController

class MovieScene : AppCompatActivity() {
    private lateinit var movieController : MovieController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_scene)
        movieController = MovieController(this)
    }
}