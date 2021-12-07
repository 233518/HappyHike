package com.example.filmatory.guis

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.TvsController
import com.example.filmatory.scenes.activities.TvsScene

/**
 * TvsGui contains all the gui elements for the tvs page
 *
 * @property tvsScene The scene to use
 * @property tvsController The controller to use
 */
class TvsGui(private val tvsScene: TvsScene, private val tvsController: TvsController) {
    var tvsRecyclerView: RecyclerView = tvsScene.findViewById(R.id.recyclerView)
    var filterBtn : Button = tvsScene.findViewById(R.id.filter_btn)
    var filterGenreBtn : Button = tvsScene.findViewById(R.id.filter_genre_btn)
    var loadingBar : ProgressBar = tvsScene.findViewById(R.id.rv_loading)
    var nestedSv : NestedScrollView = tvsScene.findViewById(R.id.nestedSv)

    init {
        filterGenreBtn.visibility = View.VISIBLE
        filterBtn.visibility = View.VISIBLE

        filterBtn.setOnClickListener {
            tvsController.showFilterList()
        }

        filterGenreBtn.setOnClickListener {
            tvsController.showGenreFilterList()
        }
    }

    /**
     * Disables the loading bar
     *
     */
    fun disableLoadingBar(){
        tvsScene.runOnUiThread {
            loadingBar.visibility = View.GONE
            tvsController.snackbarSystem.showSnackbarInfo("No more movies to load")
        }
    }
}