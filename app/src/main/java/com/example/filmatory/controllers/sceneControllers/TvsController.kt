package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.TvsScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter

class TvsController(tvsScene: TvsScene) : MainController(tvsScene) {
    val tvsScene = tvsScene

    init {
        val arrayList: MutableList<MediaItem> = ArrayList()
        var recyclerView: RecyclerView = tvsScene.findViewById(R.id.recyclerView)


        val myAdapter = RecyclerViewAdapter(arrayList, tvsScene)

        recyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
        recyclerView.adapter = myAdapter
    }
}