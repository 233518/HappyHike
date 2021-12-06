package com.example.filmatory.guis

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.TvsController
import com.example.filmatory.scenes.activities.TvsScene

class TvsGui(private val tvsScene: TvsScene, private val tvsController: TvsController) {
    var tvsRecyclerView: RecyclerView = tvsScene.findViewById(R.id.recyclerView)
    var filterBtn : Button = tvsScene.findViewById(R.id.filter_btn)
    var filterGenreBtn : Button = tvsScene.findViewById(R.id.filter_genre_btn)
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
}