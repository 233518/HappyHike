package com.example.filmatory.guis

import android.view.View
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.UpcomingTvsController
import com.example.filmatory.scenes.activities.UpcomingTvsScene

/**
 * UpcomingTvsGui contains all the gui elements for the upcoming tvs page
 *
 * @property upcomingTvsScene The scene to use
 * @property upcomingTvsController The controller to use
 */
class UpcomingTvsGui(private val upcomingTvsScene: UpcomingTvsScene, private val upcomingTvsController: UpcomingTvsController) {
    var upcomingTvsRecyclerView: RecyclerView = upcomingTvsScene.findViewById(R.id.recyclerView)
    var loadingBar : ProgressBar = upcomingTvsScene.findViewById(R.id.rv_loading)
    var nestedSv : NestedScrollView = upcomingTvsScene.findViewById(R.id.nestedSv)

    /**
     * Disables the loading bar
     *
     */
    fun disableLoadingBar(){
        upcomingTvsScene.runOnUiThread {
            loadingBar.visibility = View.GONE
            upcomingTvsController.snackbarSystem.showSnackbarInfo(upcomingTvsScene.resources.getString(R.string.no_more_tv))
        }
    }
}