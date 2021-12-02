package com.example.filmatory.scenes.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.MoviesController
import com.example.filmatory.scenes.SuperScene

/**
 * MoviesScene is the scene for showing movies
 *
 */
class MoviesScene : SuperScene(), AdapterView.OnItemSelectedListener {
    private lateinit var moviesController: MoviesController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        moviesController = MoviesController(this)
    }

    override fun onItemSelected(p0: AdapterView<*>, view: View?, pos: Int, id: Long) {
        moviesController.onNewSelected(p0.getItemAtPosition(pos))
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
