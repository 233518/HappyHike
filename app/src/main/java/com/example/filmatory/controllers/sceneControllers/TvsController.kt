package com.example.filmatory.controllers.sceneControllers

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.Tvs
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.guis.TvsGui
import com.example.filmatory.scenes.activities.TvsScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * TvsController manipulates the TvsScene gui
 *
 * @param tvsScene The TvsScene to use
 */
class TvsController(private val tvsScene: TvsScene) : MainController(tvsScene) {
    private val tvsGui = TvsGui(tvsScene, this)

    private val tvsPopularDesc : ArrayList<MediaModel> = ArrayList()
    private val tvsFilteredAZ : ArrayList<MediaModel> = ArrayList()
    private val tvsFilteredDateAsc : ArrayList<MediaModel> = ArrayList()

    private lateinit var tvsPopularAsc: ArrayList<MediaModel>
    private lateinit var tvsFilteredZA: ArrayList<MediaModel>
    private lateinit var tvsFilteredDateDesc: ArrayList<MediaModel>

    init {
        apiSystem.requestTvs(RequestBaseOptions(null, null, ::tvsData, ::onFailure))
        apiSystem.requestTvsFilterTitleAZ(RequestBaseOptions(null, null, ::tvsDataFilterTitle, ::onFailure))
        apiSystem.requestTvsFilterDateDesc(RequestBaseOptions(null, null, ::tvsDataFilterDate, ::onFailure))
    }
    fun onFailure(baseError: BaseError) {

    }

    /**
     * Sets data from API to Arraylists and displays popular tvshows descending on activity start
     *
     * @param tvs The response from API
     */
    private fun tvsData(tvs: Tvs){
        tvs.forEach { item ->
            tvsPopularDesc.add(MediaModel(DataAdapter.TYPE_TV, item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        tvsPopularAsc = ArrayList(tvsPopularDesc)
        tvsPopularAsc.reverse()

        tvsScene.runOnUiThread {
            val tvsAdapter = DataAdapter(tvsScene, tvsScene, tvsPopularDesc)
            tvsGui.tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
            tvsGui.tvsRecyclerView.adapter = tvsAdapter
            tvsAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Sets data from API to Arraylists
     *
     * @param tvs The response from API
     */
    private fun tvsDataFilterTitle(tvs: Tvs){
        tvs.forEach{
                item -> tvsFilteredAZ.add(MediaModel(DataAdapter.TYPE_TV, item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        tvsFilteredZA = ArrayList(tvsFilteredAZ)
        tvsFilteredZA.reverse()
    }

    /**
     * Sets data from API to Arraylists
     *
     * @param tvs The response from API
     */
    private fun tvsDataFilterDate(tvs: Tvs){
        tvs.forEach{
                item -> tvsFilteredDateAsc.add(MediaModel(DataAdapter.TYPE_TV, item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        tvsFilteredDateDesc = ArrayList(tvsFilteredDateAsc)
        tvsFilteredDateDesc.reverse()
    }

    /**
     * Function to display tv-shows descending by popularity
     *
     */
    private fun tvsPopularDesc(){
        tvsScene.runOnUiThread {
            val tvsAdapter = DataAdapter(tvsScene, tvsScene, tvsPopularDesc)
            tvsGui.tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
            tvsGui.tvsRecyclerView.adapter = tvsAdapter
            tvsAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display tv-shows ascending by popularity
     *
     */
    private fun tvsPopularAsc(){
        val tvsAdapter = DataAdapter(tvsScene, tvsScene, tvsPopularAsc)
        tvsScene.runOnUiThread {
            tvsGui.tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
            tvsGui.tvsRecyclerView.adapter = tvsAdapter
            tvsAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display tv-shows alphabetically
     *
     */
    private fun tvsTitleAZ(){
        val tvsAdapter = DataAdapter(tvsScene, tvsScene, tvsFilteredAZ)
        tvsScene.runOnUiThread {
            tvsGui.tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
            tvsGui.tvsRecyclerView.adapter = tvsAdapter
            tvsAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display tv-shows reversed alphabetically
     *
     */
    private fun tvsTitleZA(){
        val tvsAdapter = DataAdapter(tvsScene, tvsScene, tvsFilteredZA)
        tvsScene.runOnUiThread {
            tvsGui.tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
            tvsGui.tvsRecyclerView.adapter = tvsAdapter
            tvsAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display tv-shows ascending by date
     *
     */
    private fun tvsDateAsc(){
        val tvsAdapter = DataAdapter(tvsScene, tvsScene, tvsFilteredDateAsc)
        tvsScene.runOnUiThread {
            tvsGui.tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
            tvsGui.tvsRecyclerView.adapter = tvsAdapter
            tvsAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display tv-shows descending by date
     *
     */
    private fun tvsDateDesc(){
        val tvsAdapter = DataAdapter(tvsScene, tvsScene, tvsFilteredDateDesc)
        tvsScene.runOnUiThread {
            tvsGui.tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
            tvsGui.tvsRecyclerView.adapter = tvsAdapter
            tvsAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Listens to spinner changes, displays data accordingly
     *
     * @param itemAtPosition Chosen item in spinner
     */
    fun onNewSelected(itemAtPosition: Any) {
        when(tvsGui.spinner.selectedItemPosition){
            0 -> tvsPopularDesc()
            1 -> tvsPopularAsc()
            2 -> tvsDateAsc()
            3 -> tvsDateDesc()
            4 -> tvsTitleAZ()
            5 -> tvsTitleZA()
            else -> {
                tvsPopularDesc()
            }
        }
    }
}