package com.example.filmatory.scenes


import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.UpcomingMoviesController


class UpcomingMoviesScene : SuperScene() {
    private lateinit var upcomingMoviesController: UpcomingMoviesController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        upcomingMoviesController = UpcomingMoviesController(this)
    }
}