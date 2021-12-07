package com.example.filmatory.guis

import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.UpcomingMoviesController
import com.example.filmatory.scenes.activities.UpcomingMoviesScene

/**
 * UpcomingMoviesGui contains all the gui elements for the upcoming movies page
 *
 * @property upcomingMoviesScene The scene to use
 * @property upcomingMoviesController The controller to use
 */
class UpcomingMoviesGui(private val upcomingMoviesScene: UpcomingMoviesScene, private val upcomingMoviesController: UpcomingMoviesController) {
    var upcomingMoviesRecyclerView: RecyclerView = upcomingMoviesScene.findViewById(R.id.recyclerView)
}