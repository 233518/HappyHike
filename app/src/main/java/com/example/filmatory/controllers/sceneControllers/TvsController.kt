package com.example.filmatory.controllers.sceneControllers

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.Tvs
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.TvsGui
import com.example.filmatory.scenes.activities.TvsScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * TvsController manipulates the TvsScene gui
 *
 * @param tvsScene The TvsScene to use
 */
class TvsController(private val tvsScene: TvsScene) : MainController(tvsScene) {
    private val tvsGui = TvsGui(tvsScene, this)
    private var tempTvsArray : ArrayList<MediaModel> = ArrayList()
    private var tvsHashMap : HashMap<String, ArrayList<MediaModel>> = HashMap()

    private var genreId : Int? = null
    private var position : Int = 0
    private val maxTvs : Int = 10
    private var maxIterations : Int = 0
    private var currentFilter : String = "tvsPopularDesc"

    init {
        apiSystem.requestTvs(RequestBaseOptions(null, null, ::tvsData, ::onFailure))
        apiSystem.requestTvsFilterTitleAZ(RequestBaseOptions(null, null, ::tvsDataFilterTitle, ::onFailure))
        apiSystem.requestTvsFilterDateDesc(RequestBaseOptions(null, null, ::tvsDataFilterDate, ::onFailure))

        tvsGui.nestedSv.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight && scrollY > oldScrollY) {
                    //code to fetch more data for endless scrolling
                    loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)
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
        tvsScene.runOnUiThread {
            val tvsAdapter = DataAdapter(tvsScene, this, tvsScene, tempTvsArray)
            tvsGui.tvsRecyclerView.layoutManager = GridLayoutManager(tvsScene, 2)
            tvsGui.tvsRecyclerView.adapter = tvsAdapter
            if(maxIterations > position){
                for(i in position*maxTvs+1..(position+1)*maxTvs step 1){
                    tempTvsArray.add(array[i])
                    tvsAdapter.notifyItemInserted(i)
                }
                position++
            } else if(maxIterations == position) {
                for(i in position*maxTvs+1 until array.size step 1){
                    tempTvsArray.add(array[i])
                    tvsAdapter.notifyItemInserted(i)
                }
                position++
            } else {
                tvsGui.disableLoadingBar()
            }
        }
    }

    /**
     * Sets data from API to Arraylists and displays popular tvshows descending on activity start
     *
     * @param tvs The response from API
     */
    private fun tvsData(tvs: Tvs){
        val tvsPopularDesc : ArrayList<MediaModel> = ArrayList()
        tvs.forEach { item ->
            tvsPopularDesc.add(MediaModel(DataAdapter.TYPE_TV, item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        tvsHashMap["tvsPopularDesc"] = tvsPopularDesc
        val tvsPopularAsc: ArrayList<MediaModel> = ArrayList(tvsPopularDesc)
        tvsPopularAsc.reverse()
        tvsHashMap["tvsPopularAsc"] = tvsPopularAsc
        maxIterations = tvsPopularDesc.size / maxTvs
        loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)
    }

    /**
     * Sets data from API depending on which genre is chosen from genre filter
     *
     * @param tvs The response from the API
     */
    private fun tvsFilteredGenreData(tvs: Tvs){
        val tvsFilteredGenre : ArrayList<MediaModel> = ArrayList()
        tvsFilteredGenre.clear()
        tvs.forEach { item ->
            if(item.genre.contains(genreId)){
                tvsFilteredGenre.add(MediaModel(DataAdapter.TYPE_TV, item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
        }
        tvsHashMap["tvsFilteredGenre"] = tvsFilteredGenre
        currentFilter = "tvsFilteredGenre"
        maxIterations = tvsFilteredGenre.size / maxTvs
        loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)

        if(tvsFilteredGenre.size < 3) tvsGui.disableLoadingBar()
    }

    /**
     * Sets data from API to Arraylists
     *
     * @param tvs The response from API
     */
    private fun tvsDataFilterTitle(tvs: Tvs){
        val tvsFilteredAZ : ArrayList<MediaModel> = ArrayList()
        tvs.forEach{
                item -> tvsFilteredAZ.add(MediaModel(DataAdapter.TYPE_TV, item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        tvsHashMap["tvsFilteredAZ"] = tvsFilteredAZ
        val tvsFilteredZA: ArrayList<MediaModel> = ArrayList(tvsFilteredAZ)
        tvsFilteredZA.reverse()
        tvsHashMap["tvsFilteredZA"] = tvsFilteredZA
    }

    /**
     * Sets data from API to Arraylists
     *
     * @param tvs The response from API
     */
    private fun tvsDataFilterDate(tvs: Tvs){
        val tvsFilteredDateAsc : ArrayList<MediaModel> = ArrayList()
        tvs.forEach{
                item -> tvsFilteredDateAsc.add(MediaModel(DataAdapter.TYPE_TV, item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        tvsHashMap["tvsFilteredDateAsc"] = tvsFilteredDateAsc
        val tvsFilteredDateDesc: ArrayList<MediaModel> = ArrayList(tvsFilteredDateAsc)
        tvsFilteredDateDesc.reverse()
        tvsHashMap["tvsFilteredDateDesc"] = tvsFilteredDateDesc
    }

    /**
     * Sets genreId and requests the tv-shows to be filtered
     *
     * @param id : Id of the genre
     */
    private fun filteredGenre(id : Int){
        genreId = id
        apiSystem.requestTvs(RequestBaseOptions(null, null, ::tvsFilteredGenreData, ::onFailure))
    }

    /**
     * Opens a confirm dialog to choose filter
     * When filter is chosen, set the current filter and load it from the HashMap
     *
     */
    fun showFilterList(){
        tvsScene.runOnUiThread {
            var chosenItem: Int = -1
            MaterialAlertDialogBuilder(tvsScene)
                .setTitle(tvsScene.resources.getString(R.string.filter))
                .setNeutralButton(tvsScene.resources.getString(R.string.close_btn)) { dialog, which -> }
                .setPositiveButton(tvsScene.resources.getString(R.string.confirm_btn)) { dialog, which ->
                    tempTvsArray.clear()
                    position = 0
                    when(chosenItem){
                        0 -> {
                            currentFilter = "tvsPopularDesc"
                            loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        1 -> {
                            currentFilter = "tvsPopularAsc"
                            loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        2 -> {
                            currentFilter = "tvsFilteredDateAsc"
                            loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        3 -> {
                            currentFilter = "tvsFilteredDateDesc"
                            loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        4 -> {
                            currentFilter = "tvsFilteredAZ"
                            loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        5 -> {
                            currentFilter = "tvsFilteredZA"
                            loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        else -> {
                            currentFilter = "tvsPopularDesc"
                            loadMore(tvsHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                    }
                }
                .setSingleChoiceItems(R.array.filter_array, chosenItem) { dialog, which ->
                    chosenItem = which
                }
            .show()
        }
    }

    /**
     * Opens a confirm dialog to choose which genre to filter by
     * When chosen, run a method to filter tv-shows by genreId
     *
     */
    fun showGenreFilterList(){
        tvsScene.runOnUiThread {
            var chosenItem: Int = -1
            MaterialAlertDialogBuilder(tvsScene)
                .setTitle(tvsScene.resources.getString(R.string.filter_genre))
                .setNeutralButton(tvsScene.resources.getString(R.string.close_btn)) { dialog, which -> }
                .setPositiveButton(tvsScene.resources.getString(R.string.confirm_btn)) { dialog, which ->
                    tempTvsArray.clear()
                    position = 0
                    when(chosenItem){
                        0 -> filteredGenre(10759)
                        1 -> filteredGenre(16)
                        2 -> filteredGenre(35)
                        3 -> filteredGenre(80)
                        4 -> filteredGenre(99)
                        5 -> filteredGenre(18)
                        6 -> filteredGenre(10751)
                        7 -> filteredGenre(10762)
                        8 -> filteredGenre(10763)
                        9 -> filteredGenre(10764)
                        10 -> filteredGenre(10765)
                        11 -> filteredGenre(10766)
                        12 -> filteredGenre(10767)
                        13 -> filteredGenre(10768)
                        14 -> filteredGenre(37)
                    }
                }
                .setSingleChoiceItems(R.array.filter_genre_tv_array, chosenItem) { dialog, which ->
                    chosenItem = which
                }
            .show()
        }
    }
}