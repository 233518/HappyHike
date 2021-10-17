package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.UpcomingMovies
import com.example.filmatory.api.data.tv.UpcomingTvs
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.UpcomingTvsScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter
import com.example.filmatory.utils.TvRecyclerViewAdapter

class UpcomingTvsController(upcomingTvsScene: UpcomingTvsScene) : MainController(upcomingTvsScene) {
    val upcomingTvsScene = upcomingTvsScene
    val upcomingTvsArraylist: MutableList<MediaItem> = ArrayList()
    var upcomingTvsRecyclerView: RecyclerView = upcomingTvsScene.findViewById(R.id.recyclerView)
    val upcomingTvsAdapter = TvRecyclerViewAdapter(upcomingTvsArraylist, upcomingTvsScene)

    init {
        upcomingTvsRecyclerView.layoutManager = GridLayoutManager(upcomingTvsScene, 2)
        upcomingTvsRecyclerView.adapter = upcomingTvsAdapter
        apiSystem.requestTvsUpcoming(::upcomingTvsData)
    }
    fun upcomingTvsData(upcomingTvs: UpcomingTvs){
        upcomingTvsScene.runOnUiThread(Runnable {
            upcomingTvs.forEach{
                item -> upcomingTvsArraylist.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            upcomingTvsAdapter.notifyDataSetChanged()
        })
    }
}