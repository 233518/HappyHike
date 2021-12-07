package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.ListController
import com.example.filmatory.scenes.SuperScene

/**
 * ListScene contains all the components for creating a list page
 *
 */
class ListScene : SuperScene() {
    private lateinit var listController : ListController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_scene)
        listController = ListController(this)
    }
}