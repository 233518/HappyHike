package com.example.filmatory.scenes.activities

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.TvsController
import com.example.filmatory.scenes.SuperScene

/**
 * TvsScene contains all the components for creating a tvs page
 *
 */
class TvsScene : SuperScene() {
    private lateinit var tvsController: TvsController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_media_recyclerview)
        tvsController = TvsController(this)
    }
}