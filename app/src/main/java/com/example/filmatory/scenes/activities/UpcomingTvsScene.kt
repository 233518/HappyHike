package com.example.filmatory.scenes.activities


import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.UpcomingTvsController
import com.example.filmatory.scenes.SuperScene

/**
 * UpcomingTvsScene is the scene for showing upcoming tvs
 *
 */
class UpcomingTvsScene : SuperScene() {
    private lateinit var upcomingTvsController: UpcomingTvsController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        upcomingTvsController = UpcomingTvsController(this)
    }
}