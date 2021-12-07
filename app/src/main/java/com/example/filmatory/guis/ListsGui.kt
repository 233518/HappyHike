package com.example.filmatory.guis

import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.ListsController
import com.example.filmatory.scenes.activities.ListsScene

/**
 * ListsGui contains all the gui elements for the lists page
 *
 * @property listsScene The scene to use
 * @property listsController The controller to use
 */
class ListsGui(private val listsScene: ListsScene, private val listsController: ListsController) {
    var listsRecyclerView: RecyclerView = listsScene.findViewById(R.id.recyclerView)
}