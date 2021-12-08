package com.example.filmatory.controllers.sceneControllers

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmatory.api.data.tv.UpcomingTvs
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.UpcomingTvsGui
import com.example.filmatory.scenes.activities.UpcomingTvsScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * UpcomingTvsController controls everything related to the upcoming tvs page
 *
 * @param upcomingTvsScene The UpcomingTvsScene to use
 */
class UpcomingTvsController(private val upcomingTvsScene: UpcomingTvsScene) : MainController(upcomingTvsScene) {
    private val upcomingTvsGui = UpcomingTvsGui(upcomingTvsScene, this)
    private val upcomingTvsArraylist : ArrayList<MediaModel> = ArrayList()
    private var tempTvsArray : ArrayList<MediaModel> = ArrayList()
    private var position : Int = 0
    private val maxTvs : Int = 11
    private var maxIterations : Int = 0

    init {
        apiSystem.requestTvsUpcoming(RequestBaseOptions(null, null, ::upcomingTvsData, ::onFailure))
        upcomingTvsGui.nestedSv.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight && scrollY > oldScrollY) {
                    loadMore(upcomingTvsArraylist)
                }
            }
        }
    }

    /**
     * Method to load 10 by 10 tv-shows as the user scrolls to bottom
     *
     * @param array : Array to be loaded
     */
    private fun loadMore(array : ArrayList<MediaModel>){
        upcomingTvsScene.runOnUiThread {
            val tvsAdapter = DataAdapter(upcomingTvsScene, this, upcomingTvsScene, tempTvsArray)
            upcomingTvsGui.upcomingTvsRecyclerView.layoutManager = GridLayoutManager(upcomingTvsScene, 2)
            upcomingTvsGui.upcomingTvsRecyclerView.adapter = tvsAdapter
            if(maxIterations > position){
                for(i in position*(maxTvs+1)..(position+1)*maxTvs step 1){
                    tempTvsArray.add(array[i])
                    tvsAdapter.notifyItemInserted(i)
                }
                position++
            } else if(maxIterations == position) {
                for(i in position*(maxTvs+1) until array.size step 1){
                    tempTvsArray.add(array[i])
                    tvsAdapter.notifyItemInserted(i)
                }
                position++
            } else {
                upcomingTvsGui.disableLoadingBar()
            }
        }
    }

    /**
     * Update the gui with data from API
     *
     * @param upcomingTvs The response from API
     */
    private fun upcomingTvsData(upcomingTvs: UpcomingTvs){
        upcomingTvs.forEach { item ->
            upcomingTvsArraylist.add(
                MediaModel(
                    DataAdapter.TYPE_TV,
                    item.title,
                    item.releaseDate,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                    item.id
                )
            )
        }
        maxIterations = upcomingTvsArraylist.size / maxTvs
        loadMore(upcomingTvsArraylist)
    }
}