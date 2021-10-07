package com.example.filmatory.scenes

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.MoviesController

class MoviesScene : SuperScene() {
    private lateinit var moviesController: MoviesController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        moviesController = MoviesController(this)
    }
}
