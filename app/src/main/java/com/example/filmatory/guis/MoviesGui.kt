package com.example.filmatory.guis

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.MoviesController
import com.example.filmatory.scenes.activities.MoviesScene

/**
 * MoviesGui contains all the gui elements for the movies page
 *
 * @property moviesScene The scene to use
 * @property moviesController The controller to use
 */
class MoviesGui(private val moviesScene: MoviesScene, private val moviesController: MoviesController) {
    var moviesRecyclerView: RecyclerView = moviesScene.findViewById(R.id.recyclerView)
    var filterBtn : Button = moviesScene.findViewById(R.id.filter_btn)
    var filterGenreBtn : Button = moviesScene.findViewById(R.id.filter_genre_btn)
    var loadingBar : ProgressBar = moviesScene.findViewById(R.id.rv_loading)
    var nestedSv : NestedScrollView = moviesScene.findViewById(R.id.nestedSv)

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

    /**
     * Disables the loading bar
     *
     */
    fun disableLoadingBar(){
        moviesScene.runOnUiThread {
            loadingBar.visibility = View.GONE
            moviesController.snackbarSystem.showSnackbarInfo(moviesScene.resources.getString(R.string.no_more_movies))
        }
    }
}