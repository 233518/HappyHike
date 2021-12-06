package com.example.filmatory.guis

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.MoviesController
import com.example.filmatory.scenes.activities.MoviesScene

class MoviesGui(private val moviesScene: MoviesScene, private val moviesController: MoviesController) {
    var moviesRecyclerView: RecyclerView = moviesScene.findViewById(R.id.recyclerView)
    var filterBtn : Button = moviesScene.findViewById(R.id.filter_btn)
    var filterGenreBtn : Button = moviesScene.findViewById(R.id.filter_genre_btn)

    init {
        filterGenreBtn.visibility = View.VISIBLE
        filterBtn.visibility = View.VISIBLE

        filterBtn.setOnClickListener {
            moviesController.showFilterList()
        }

        filterGenreBtn.setOnClickListener {
            moviesController.showGenreFilterList()
        }
    }
}