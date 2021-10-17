package com.example.filmatory.controllers.sceneControllers

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.UpcomingMovies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.UpcomingMoviesScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter
import com.example.filmatory.utils.TvRecyclerViewAdapter

class UpcomingMoviesController(upcomingMoviesScene: UpcomingMoviesScene) : MainController(upcomingMoviesScene) {
    val upcomingMoviesScene = upcomingMoviesScene
    var upcomingMoviesRecyclerView: RecyclerView = upcomingMoviesScene.findViewById(R.id.recyclerView)
    var upcomingMoviesArrayList: MutableList<MediaItem> = ArrayList()
    var upcomingMoviesAdapter = RecyclerViewAdapter(upcomingMoviesArrayList, upcomingMoviesScene)

    init {
        upcomingMoviesRecyclerView.layoutManager = GridLayoutManager(upcomingMoviesScene, 2)
        upcomingMoviesRecyclerView.adapter = upcomingMoviesAdapter
        apiSystem.requestMovieUpcoming(::upcomingMoviesData)
    }
    fun upcomingMoviesData(upcomingMovies: UpcomingMovies){
        upcomingMoviesScene.runOnUiThread(Runnable {
            upcomingMovies.forEach{
                item -> upcomingMoviesArrayList.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            upcomingMoviesAdapter.notifyDataSetChanged()
        })
    }
}