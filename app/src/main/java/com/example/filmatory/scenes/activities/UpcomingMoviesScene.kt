package com.example.filmatory.scenes.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.UpcomingMoviesController
import com.example.filmatory.utils.BlurImage


class UpcomingMoviesScene : SuperScene() {
    private lateinit var upcomingMoviesController: UpcomingMoviesController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        upcomingMoviesController = UpcomingMoviesController(this)
    }
}