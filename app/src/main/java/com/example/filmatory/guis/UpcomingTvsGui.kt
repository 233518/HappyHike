package com.example.filmatory.guis

import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.UpcomingTvsController
import com.example.filmatory.scenes.activities.UpcomingTvsScene

/**
 * UpcomingTvsGui contains all the gui elements for the upcoming tvs page
 *
 * @property upcomingTvsScene The scene to use
 * @property upcomingTvsController The controller to use
 */
class UpcomingTvsGui(private val upcomingTvsScene: UpcomingTvsScene, private val upcomingTvsController: UpcomingTvsController) {
    var upcomingTvsRecyclerView: RecyclerView = upcomingTvsScene.findViewById(R.id.recyclerView)
}