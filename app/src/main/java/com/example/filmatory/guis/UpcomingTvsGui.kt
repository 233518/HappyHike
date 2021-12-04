package com.example.filmatory.guis

import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.UpcomingTvsController
import com.example.filmatory.scenes.activities.UpcomingTvsScene

class UpcomingTvsGui(private val upcomingTvsScene: UpcomingTvsScene, private val upcomingTvsController: UpcomingTvsController) {
    var upcomingTvsRecyclerView: RecyclerView = upcomingTvsScene.findViewById(R.id.recyclerView)
}