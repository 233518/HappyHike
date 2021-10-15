package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.UpcomingTvsScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter

class UpcomingTvsController(upcomingTvsScene: UpcomingTvsScene) : MainController(upcomingTvsScene) {
    val upcomingTvsScene = upcomingTvsScene
    init {
        val arrayList: MutableList<MediaItem> = ArrayList()
        var recyclerView: RecyclerView = upcomingTvsScene.findViewById(R.id.recyclerView)


        val myAdapter = RecyclerViewAdapter(arrayList, upcomingTvsScene)

        recyclerView.layoutManager = GridLayoutManager(upcomingTvsScene, 2)
        recyclerView.adapter = myAdapter
    }
}