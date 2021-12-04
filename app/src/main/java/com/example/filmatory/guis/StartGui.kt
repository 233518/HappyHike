package com.example.filmatory.guis

import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.StartController
import com.example.filmatory.scenes.activities.StartScene

class StartGui(private val startScene: StartScene, private val startController: StartController) {
    val discoverTvsRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view2)
    val discoverMoviesRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view)
    val recTvsRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view4)
    val recMoviesRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view3)
}