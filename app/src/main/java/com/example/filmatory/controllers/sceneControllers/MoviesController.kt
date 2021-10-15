package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.MoviesScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter

class MoviesController(moviesScene: MoviesScene) : MainController(moviesScene) {
    val moviesScene = moviesScene

    init {
        val arrayList: MutableList<MediaItem> = ArrayList()
        var recyclerView: RecyclerView = moviesScene.findViewById(R.id.recyclerView)


        val myAdapter = RecyclerViewAdapter(arrayList, moviesScene)

        recyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
        recyclerView.adapter = myAdapter
    }
}