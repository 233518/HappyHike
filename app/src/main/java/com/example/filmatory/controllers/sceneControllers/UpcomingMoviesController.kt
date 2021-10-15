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
    var recyclerView: RecyclerView = upcomingMoviesScene.findViewById(R.id.recyclerView);
    var arrayList: MutableList<MediaItem> = ArrayList()
    var upcomingMoviesAdapter = RecyclerViewAdapter(arrayList, upcomingMoviesScene)

    init {
        recyclerView.layoutManager = GridLayoutManager(upcomingMoviesScene, 2);
        recyclerView.adapter = upcomingMoviesAdapter;
        apiSystem.requestMovieUpcoming(::upcomingMoviesData)
    }
    fun upcomingMoviesData(upcomingMovies: UpcomingMovies){
        upcomingMoviesScene.runOnUiThread(Runnable {
            upcomingMovies.forEach{
                item -> arrayList.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            upcomingMoviesAdapter.notifyDataSetChanged();
        })

    }
}