package com.example.filmatory.guis

import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.UpcomingMoviesController
import com.example.filmatory.scenes.activities.UpcomingMoviesScene

class UpcomingMoviesGui(private val upcomingMoviesScene: UpcomingMoviesScene, private val upcomingMoviesController: UpcomingMoviesController) {
    var upcomingMoviesRecyclerView: RecyclerView = upcomingMoviesScene.findViewById(R.id.recyclerView)
}