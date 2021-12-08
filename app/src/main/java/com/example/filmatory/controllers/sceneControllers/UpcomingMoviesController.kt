package com.example.filmatory.controllers.sceneControllers

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmatory.api.data.movie.UpcomingMovies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.UpcomingMoviesGui
import com.example.filmatory.scenes.activities.UpcomingMoviesScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * UpcomingMoviesController controls everything related to the upcoming movies page
 *
 * @param upcomingMoviesScene The UpcomingTvsScene to use
 */
class UpcomingMoviesController(private val upcomingMoviesScene: UpcomingMoviesScene) : MainController(upcomingMoviesScene) {
    private val upcomingMoviesGui = UpcomingMoviesGui(upcomingMoviesScene, this)
    private var upcomingMoviesArrayList : ArrayList<MediaModel> = ArrayList()
    private var tempMoviesArray : ArrayList<MediaModel> = ArrayList()
    private var position : Int = 0
    private val maxMovies : Int = 11
    private var maxIterations : Int = 0

    init {
        apiSystem.requestMovieUpcoming(RequestBaseOptions(null, null, ::upcomingMoviesData, ::onFailure))
        upcomingMoviesGui.nestedSv.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight && scrollY > oldScrollY) {
                    loadMore(upcomingMoviesArrayList)
                }
            }
        }
    }

    /**
     * Method to load 10 by 10 movies as the user scrolls to bottom
     *
     * @param array : Array to be loaded
     */
    private fun loadMore(array : ArrayList<MediaModel>){
        upcomingMoviesScene.runOnUiThread {
            val moviesAdapter = DataAdapter(upcomingMoviesScene, this, upcomingMoviesScene, tempMoviesArray)
            upcomingMoviesGui.upcomingMoviesRecyclerView.layoutManager = GridLayoutManager(upcomingMoviesScene, 2)
            upcomingMoviesGui.upcomingMoviesRecyclerView.adapter = moviesAdapter
            if(maxIterations > position){
                for(i in position*(maxMovies+1)..(position+1)*maxMovies step 1){
                    tempMoviesArray.add(array[i])
                    moviesAdapter.notifyItemInserted(i)
                }
                position++
            } else if(maxIterations == position) {
                for(i in position*(maxMovies+1) until array.size step 1){
                    tempMoviesArray.add(array[i])
                    moviesAdapter.notifyItemInserted(i)
                }
                position++
            } else {
                upcomingMoviesGui.disableLoadingBar()
            }
        }
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
        maxIterations = upcomingMoviesArrayList.size / maxMovies
        loadMore(upcomingMoviesArrayList)
    }
}