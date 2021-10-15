package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.MoviesScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter

class MoviesController(moviesScene: MoviesScene) : MainController(moviesScene) {
    val moviesScene = moviesScene
    val moviesArrayList: MutableList<MediaItem> = ArrayList()
    val moviesRecyclerView: RecyclerView = moviesScene.findViewById(R.id.recyclerView)
    val moviesAdapter = RecyclerViewAdapter(moviesArrayList, moviesScene)

    init {
        moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
        moviesRecyclerView.adapter = moviesAdapter
        apiSystem.requestMovies(::moviesData)
    }
    fun moviesData(movies: Movies){
        moviesScene.runOnUiThread(Runnable {
            movies.forEach{
                    item -> moviesArrayList.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            moviesAdapter.notifyDataSetChanged()
        })
    }
}