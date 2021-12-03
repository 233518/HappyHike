package com.example.filmatory.guis

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.MoviesController
import com.example.filmatory.scenes.activities.MoviesScene

class MoviesGui(private val moviesScene: MoviesScene, private val moviesController: MoviesController) {
    var moviesRecyclerView: RecyclerView = moviesScene.findViewById(R.id.recyclerView)
    var spinner : Spinner = moviesScene.findViewById(R.id.filter_spinner)

    init {
        ArrayAdapter.createFromResource(moviesScene, R.array.filter_array, android.R.layout.simple_spinner_dropdown_item).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.visibility = View.VISIBLE
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = moviesScene
    }
}