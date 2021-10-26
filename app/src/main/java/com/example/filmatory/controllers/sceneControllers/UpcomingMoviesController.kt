package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.UpcomingMovies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.UpcomingMoviesScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter

/**
 * UpcomingMoviesController manipulates the UpcomingMoviesController gui
 *
 * @param upcomingMoviesScene The UpcomingTvsScene to use
 */
class UpcomingMoviesController(private val upcomingMoviesScene: UpcomingMoviesScene) : MainController(upcomingMoviesScene) {
    private var upcomingMoviesRecyclerView: RecyclerView = upcomingMoviesScene.findViewById(R.id.recyclerView)
    private var upcomingMoviesArrayList: MutableList<MediaItem> = ArrayList()
    private var upcomingMoviesAdapter = RecyclerViewAdapter(upcomingMoviesArrayList, upcomingMoviesScene)

    init {
        upcomingMoviesRecyclerView.layoutManager = GridLayoutManager(upcomingMoviesScene, 2)
        upcomingMoviesRecyclerView.adapter = upcomingMoviesAdapter
        apiSystem.requestMovieUpcoming(::upcomingMoviesData)
    }

    /**
     * Update the gui with data from API
     *
     * @param upcomingMovies The response from API
     */
    private fun upcomingMoviesData(upcomingMovies: UpcomingMovies){
        upcomingMoviesScene.runOnUiThread(Runnable {
            upcomingMovies.forEach{
                item -> upcomingMoviesArrayList.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            upcomingMoviesAdapter.notifyDataSetChanged()
        })
    }
}