package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.UpcomingMovies
import com.example.filmatory.api.data.tv.TvFrontpage
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.UpcomingMoviesScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter
import com.example.filmatory.utils.SliderAdapter

class UpcomingMoviesController(upcomingMoviesScene: UpcomingMoviesScene) : MainController(upcomingMoviesScene) {
    val upcomingMoviesScene = upcomingMoviesScene
    init {
        apiSystem.requestMovieUpcoming(::upcomingMoviesData)

    }
    fun upcomingMoviesData(upcomingMovies: UpcomingMovies){
        upcomingMoviesScene.runOnUiThread(Runnable {
            val arrayList: MutableList<MediaItem> = ArrayList()
            val upcomingMoviesAdapter = RecyclerViewAdapter(arrayList, upcomingMoviesScene)
            val recyclerView: RecyclerView = upcomingMoviesScene.findViewById(R.id.recyclerView)
            upcomingMovies.forEach{
                item -> arrayList.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            recyclerView.layoutManager = GridLayoutManager(upcomingMoviesScene, 2)
            recyclerView.adapter = upcomingMoviesAdapter
        })
    }
}