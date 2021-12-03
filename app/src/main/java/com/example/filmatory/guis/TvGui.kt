package com.example.filmatory.guis

import android.widget.ImageButton
import android.widget.TextView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.TvController
import com.example.filmatory.scenes.activities.TvScene

class TvGui(private var tvScene: TvScene, private var tvController: TvController) {
    private val favoriteBtn : ImageButton = tvScene.findViewById(R.id.movie_favorite_icon)
    private val watchlistBtn : ImageButton = tvScene.findViewById(R.id.movie_watchlist_icon)
    private val addToListBtn : TextView = tvScene.findViewById(R.id.movie_addtolist_btn)
}