package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.TvController
import com.example.filmatory.scenes.SuperScene

/**
 * TvScene is the scene for showing tv information
 *
 */
class TvScene : SuperScene() {
    private lateinit var tvController: TvController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_scene)
        tvController = TvController(this)
    }
}