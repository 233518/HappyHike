package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.MovieFrontpage
import com.example.filmatory.api.data.tv.TvFrontpage
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.StartScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.SliderAdapter

class StartController(startScene: StartScene) : MainController(startScene) {
    val startScene = startScene

    init {
        apiSystem.requestMovieFrontpage(::discoverMoviesData)
        apiSystem.requestTvFrontpage(::discoverTvData)
        apiSystem.requestMovieFrontpage(::recMovieData)
        apiSystem.requestTvFrontpage(::recTvData)
    }
    fun discoverMoviesData(movieFrontpage: MovieFrontpage){
        startScene.runOnUiThread(Runnable {
            val discoverMoviesArraylist: MutableList<MediaItem> = ArrayList()
            val discoverMoviesAdapter = SliderAdapter(discoverMoviesArraylist,startScene)
            val discoverMoviesRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view)
            movieFrontpage.forEach{
                    item -> discoverMoviesArraylist.add(MediaItem(item.original_title, item.release_date, R.drawable.movie1))
            }
            discoverMoviesRecyclerView.layoutManager = LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            discoverMoviesRecyclerView.adapter = discoverMoviesAdapter
        })
    }

    fun discoverTvData(tvFrontpage: TvFrontpage){
        startScene.runOnUiThread(Runnable {
            val discoverTvsArrayList: MutableList<MediaItem> = ArrayList()
            val discoverTvsAdapter = SliderAdapter(discoverTvsArrayList,startScene)
            val discoverTvsRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view2)
            tvFrontpage.forEach{
                    item -> discoverTvsArrayList.add(MediaItem(item.name, item.first_air_date, R.drawable.movie6))
            }
            discoverTvsRecyclerView.layoutManager = LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            discoverTvsRecyclerView.adapter = discoverTvsAdapter
        })
    }

    fun recMovieData(movieFrontpage: MovieFrontpage){
        startScene.runOnUiThread(Runnable {
            val recMoviesArrayList: MutableList<MediaItem> = ArrayList()
            val redMoviesAdapter = SliderAdapter(recMoviesArrayList, startScene)
            val recMoviesRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view3)
            movieFrontpage.forEach{
                    item -> recMoviesArrayList.add(MediaItem(item.original_title, item.release_date, R.drawable.movie15))
            }
            recMoviesRecyclerView.layoutManager = LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            recMoviesRecyclerView.adapter = redMoviesAdapter
        })
    }

    fun recTvData(tvFrontpage: TvFrontpage){
        startScene.runOnUiThread(Runnable {
            val recTvsArrayList: MutableList<MediaItem> = ArrayList()
            val recTvsAdapter = SliderAdapter(recTvsArrayList, startScene)
            val recTvsRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view4)
            tvFrontpage.forEach{
                    item -> recTvsArrayList.add(MediaItem(item.name, item.first_air_date, R.drawable.movie18))
            }
            recTvsRecyclerView.layoutManager = LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            recTvsRecyclerView.adapter = recTvsAdapter
        })
    }
}