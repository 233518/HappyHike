package com.example.filmatory.scenes.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.SearchController
import com.example.filmatory.scenes.SuperScene

class SearchScene : SuperScene() {
    private lateinit var searchController : SearchController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_scene)
        searchController = SearchController(this)
    }
}