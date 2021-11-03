package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.MoviesScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.items.MediaItem
import com.example.filmatory.utils.adapters.RecyclerViewAdapter

/**
 * MoviesController manipulates the MoviesScene gui
 *
 * @param moviesScene The MoviesScene to use
 */
class MoviesController(private val moviesScene: MoviesScene) : MainController(moviesScene) {
    private val moviesArrayList: MutableList<MediaItem> = ArrayList()
    private val moviesRecyclerView: RecyclerView = moviesScene.findViewById(R.id.recyclerView)
    private val moviesAdapter = RecyclerViewAdapter(moviesArrayList, moviesScene)

    init {
        moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
        moviesRecyclerView.adapter = moviesAdapter
        apiSystem.requestMovies(RequestBaseOptions(null, null, ::moviesData, ::onFailure))
    }

    fun onFailure(baseError: BaseError) {

    }

    /**
     * Update the gui with data from API
     *
     * @param movies The response from API
     */
    private fun moviesData(movies: Movies){
        moviesScene.runOnUiThread(Runnable {
            movies.forEach{
                    item -> moviesArrayList.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            moviesAdapter.notifyDataSetChanged()
        })
    }
}