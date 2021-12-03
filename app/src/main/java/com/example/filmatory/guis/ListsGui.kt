package com.example.filmatory.guis

import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.ListsController
import com.example.filmatory.scenes.activities.ListsScene

class ListsGui(private val listsScene: ListsScene, private val listsController: ListsController) {
    var listsRecyclerView: RecyclerView = listsScene.findViewById(R.id.recyclerView)
}