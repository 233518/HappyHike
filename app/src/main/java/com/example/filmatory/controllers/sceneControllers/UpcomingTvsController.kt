package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.UpcomingTvs
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.UpcomingTvsScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * UpcomingTvsController manipulates the UpcomingTvs gui
 *
 * @param upcomingTvsScene The UpcomingTvsScene to use
 */
class UpcomingTvsController(private val upcomingTvsScene: UpcomingTvsScene) : MainController(upcomingTvsScene) {
    private val upcomingTvsArraylist : ArrayList<MediaModel> = ArrayList()
    private var upcomingTvsRecyclerView: RecyclerView = upcomingTvsScene.findViewById(R.id.recyclerView)
    private val upcomingTvsAdapter = DataAdapter(upcomingTvsScene, upcomingTvsArraylist)

    init {
        apiSystem.requestTvsUpcoming(RequestBaseOptions(null, null, ::upcomingTvsData, ::onFailure))
        upcomingTvsRecyclerView.layoutManager = GridLayoutManager(upcomingTvsScene, 2)
        upcomingTvsRecyclerView.adapter = upcomingTvsAdapter
    }

    fun onFailure(baseError: BaseError) {

    }

    /**
     * Update the gui with data from API
     *
     * @param upcomingTvs The response from API
     */
    private fun upcomingTvsData(upcomingTvs: UpcomingTvs){
        upcomingTvsScene.runOnUiThread {
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
            upcomingTvsAdapter.notifyDataSetChanged()
        }
    }
}