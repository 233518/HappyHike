package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.Tvs
import com.example.filmatory.api.data.tv.UpcomingTvs
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.TvsScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter
import com.example.filmatory.utils.TvRecyclerViewAdapter

class TvsController(tvsScene: TvsScene) : MainController(tvsScene) {
    val tvsScene = tvsScene
    val tvsArrayList: MutableList<MediaItem> = ArrayList()
    var tvsRecyclerView: RecyclerView = tvsScene.findViewById(R.id.recyclerView)
    val tvsAdapter = TvRecyclerViewAdapter(tvsArrayList, tvsScene)
    init {
        tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
        tvsRecyclerView.adapter = tvsAdapter
        apiSystem.requestTvs(::tvsData)
    }
    fun tvsData(tvs: Tvs){
        tvsScene.runOnUiThread(Runnable {
            tvs.forEach{
                    item -> tvsArrayList.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            tvsAdapter.notifyDataSetChanged()
        })
    }
}