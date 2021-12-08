package com.example.filmatory.guis

import android.view.View
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.UpcomingMoviesController
import com.example.filmatory.scenes.activities.UpcomingMoviesScene

/**
 * UpcomingMoviesGui contains all the gui elements for the upcoming movies page
 *
 * @property upcomingMoviesScene The scene to use
 * @property upcomingMoviesController The controller to use
 */
class UpcomingMoviesGui(private val upcomingMoviesScene: UpcomingMoviesScene, private val upcomingMoviesController: UpcomingMoviesController) {
    var upcomingMoviesRecyclerView: RecyclerView = upcomingMoviesScene.findViewById(R.id.recyclerView)
    var loadingBar : ProgressBar = upcomingMoviesScene.findViewById(R.id.rv_loading)
    var nestedSv : NestedScrollView = upcomingMoviesScene.findViewById(R.id.nestedSv)

    /**
     * Disables the loading bar
     *
     */
    fun disableLoadingBar(){
        upcomingMoviesScene.runOnUiThread {
            loadingBar.visibility = View.GONE
            upcomingMoviesController.snackbarSystem.showSnackbarInfo(upcomingMoviesScene.resources.getString(R.string.no_more_movies))
        }
    }
}