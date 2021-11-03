package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.Tvs
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.TvsScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.items.MediaItem
import com.example.filmatory.utils.adapters.TvRecyclerViewAdapter

/**
 * TvsController manipulates the TvsScene gui
 *
 * @param tvsScene The TvsScene to use
 */
class TvsController(private val tvsScene: TvsScene) : MainController(tvsScene) {
    private val tvsArrayList: MutableList<MediaItem> = ArrayList()
    private var tvsRecyclerView: RecyclerView = tvsScene.findViewById(R.id.recyclerView)
    private val tvsAdapter = TvRecyclerViewAdapter(tvsArrayList, tvsScene)
    init {
        tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
        tvsRecyclerView.adapter = tvsAdapter
        apiSystem.requestTvs(RequestBaseOptions(null, null, ::tvsData, ::onFailure))
    }

    fun onFailure(baseError: BaseError) {

    }

    /**
     * Update the gui with data from API
     *
     * @param tvs The response from API
     */
    private fun tvsData(tvs: Tvs){
        tvsScene.runOnUiThread(Runnable {
            tvs.forEach{
                    item -> tvsArrayList.add(MediaItem(item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            tvsAdapter.notifyDataSetChanged()
        })
    }
}