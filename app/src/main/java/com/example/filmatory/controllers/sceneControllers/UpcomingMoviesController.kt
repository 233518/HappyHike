package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.UpcomingMovies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
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
    private var upcomingMoviesArrayList : ArrayList<MediaModel> = ArrayList()
    private var upcomingMoviesRecyclerView: RecyclerView = upcomingMoviesScene.findViewById(R.id.recyclerView)
    private var upcomingMoviesAdapter = DataAdapter(upcomingMoviesScene, upcomingMoviesScene, upcomingMoviesArrayList)

    init {
        apiSystem.requestMovieUpcoming(RequestBaseOptions(null, null, ::upcomingMoviesData, ::onFailure))
        upcomingMoviesRecyclerView.layoutManager = GridLayoutManager(upcomingMoviesScene, 2)
        upcomingMoviesRecyclerView.adapter = upcomingMoviesAdapter
    }

    private fun upcomingMoviesData(upcomingMovies: UpcomingMovies){
        upcomingMoviesScene.runOnUiThread {
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
            upcomingMoviesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Update the gui with data from API
     *
     * @param upcomingMovies The response from API
     */
/*    private fun upcomingMoviesData(upcomingMovies: UpcomingMovies){
        upcomingMoviesScene.runOnUiThread(Runnable {
            upcomingMovies.forEach{
                item -> upcomingMoviesArrayList.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            upcomingMoviesAdapter.notifyDataSetChanged()
        })
    }*/
}