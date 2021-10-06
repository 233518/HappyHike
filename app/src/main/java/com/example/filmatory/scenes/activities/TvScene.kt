package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.TvController
import com.example.filmatory.scenes.SuperScene

class TvScene : SuperScene() {
    private lateinit var tvController: TvController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_scene)
        tvController = TvController(this)
    }
}