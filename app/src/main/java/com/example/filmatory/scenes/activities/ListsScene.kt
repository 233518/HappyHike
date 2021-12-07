package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.ListsController
import com.example.filmatory.scenes.SuperScene

/**
 * ListsScene contains all the components for creating a lists page
 *
 */
class ListsScene : SuperScene() {
    private lateinit var listController : ListsController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lists_container)
        listController = ListsController(this)
    }
}