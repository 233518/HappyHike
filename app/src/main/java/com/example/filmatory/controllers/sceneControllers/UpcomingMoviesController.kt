package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmatory.api.data.movie.UpcomingMovies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.UpcomingMoviesGui
import com.example.filmatory.scenes.activities.UpcomingMoviesScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * UpcomingMoviesController manipulates the UpcomingMoviesController gui
 *
 * @param upcomingMoviesScene The UpcomingTvsScene to use
 */
class UpcomingMoviesController(private val upcomingMoviesScene: UpcomingMoviesScene) : MainController(upcomingMoviesScene) {
    private val upcomingMoviesGui = UpcomingMoviesGui(upcomingMoviesScene, this)
    private var upcomingMoviesArrayList : ArrayList<MediaModel> = ArrayList()
    private var upcomingMoviesAdapter = DataAdapter(upcomingMoviesScene, this, upcomingMoviesScene, upcomingMoviesArrayList)

    init {
        apiSystem.requestMovieUpcoming(RequestBaseOptions(null, null, ::upcomingMoviesData, ::onFailure))
        upcomingMoviesGui.upcomingMoviesRecyclerView.layoutManager = GridLayoutManager(upcomingMoviesScene, 2)
        upcomingMoviesGui.upcomingMoviesRecyclerView.adapter = upcomingMoviesAdapter
    }

    /**
     * Update the gui with data from API
     *
     * @param upcomingMovies The response from API
     */
    private fun upcomingMoviesData(upcomingMovies: UpcomingMovies){
        upcomingMovies.forEach { item ->
            upcomingMoviesArrayList.add(
                MediaModel(
                    DataAdapter.TYPE_MOVIE,
                    item.title,
                    item.releaseDate,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                    item.id
                )
            )
        }
        upcomingMoviesScene.runOnUiThread {
            upcomingMoviesAdapter.notifyDataSetChanged()
        }
    }
}