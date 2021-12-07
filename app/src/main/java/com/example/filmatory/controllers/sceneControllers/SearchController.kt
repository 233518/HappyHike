package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmatory.api.data.search.Search
import com.example.filmatory.api.data.search.SearchItem
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.SearchGui
import com.example.filmatory.scenes.activities.SearchScene
import com.example.filmatory.systems.ApiSystem
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * SearchController controls everything related to the search page
 *
 * @property searchScene : The searchScene to use
 */
class SearchController(private val searchScene : SearchScene) : MainController(searchScene) {
    private val searchGui = SearchGui(searchScene, this)
    private val intent: Intent = searchScene.intent
    private val title = intent.getStringExtra("title")

    private var movieListArrayList: ArrayList<MediaModel> = ArrayList()
    private var tvListArrayList: ArrayList<MediaModel> = ArrayList()
    private var movieListAdapter = DataAdapter(searchScene,this, searchScene, movieListArrayList)
    private var tvListAdapter = DataAdapter(searchScene,this, searchScene, tvListArrayList)

    private lateinit var mediaSorted : MediaSorted

    private var firstRun = true

    private inner class MediaSorted(val movieArray: ArrayList<SearchItem>, val tvArray: ArrayList<SearchItem>)

    init {
        searchGui.resultRecyclerView.layoutManager = LinearLayoutManager(searchScene, LinearLayoutManager.VERTICAL, false)
        val concatAdapter = ConcatAdapter(movieListAdapter, tvListAdapter)
        searchGui.resultRecyclerView.adapter = concatAdapter

        apiSystem.requestSearch(ApiSystem.RequestBaseOptions(null, null, ::onSearch, ::onFailure), title!!, languageCode)
    }

    /**
     * Update the gui with the data from API
     *
     * @param search : Response from API
     */
    private fun onSearch(search: Search) {
        mediaSorted = sortResult(search)
        showAll()
    }

    /**
     * Method for showing all results
     *
     */
    fun showAll() {
        if(!firstRun) {
            movieListArrayList.clear()
            tvListArrayList.clear()
        }
        for(movie in mediaSorted.movieArray) {
            movieListArrayList.add(MediaModel(DataAdapter.TYPE_SEARCH_MOVIE , movie.title, movie.overview,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movie.poster_path, movie.id))
        }

        for(tv in mediaSorted.tvArray) {
            tvListArrayList.add(MediaModel(DataAdapter.TYPE_SEARCH_TV , tv.title, tv.overview,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + tv.poster_path, tv.id))
        }
        notifyAdapater()
    }

    /**
     * Method for showing only movies
     *
     */
    fun showMovie() {
        movieListArrayList.clear()
        tvListArrayList.clear()
        for(movie in mediaSorted.movieArray) {
            movieListArrayList.add(MediaModel(DataAdapter.TYPE_SEARCH_MOVIE , movie.title, movie.overview,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movie.poster_path, movie.id))
        }
        notifyAdapater()
    }

    /**
     * Method for showing only TV
     *
     */
    fun showTv() {
        movieListArrayList.clear()
        tvListArrayList.clear()
        for(tv in mediaSorted.tvArray) {
            tvListArrayList.add(MediaModel(DataAdapter.TYPE_SEARCH_TV , tv.title, tv.overview,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + tv.poster_path, tv.id))
        }
        notifyAdapater()
    }

    /**
     * Notifies the adapters that data has changed
     *
     */
    private fun notifyAdapater() {
        searchScene.runOnUiThread {
            tvListAdapter.notifyDataSetChanged()
            movieListAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Changes the data to correct filter
     *
     * @param itemAtPosition Position of the selection
     */
    fun onNewSelected(itemAtPosition: Int) {
        when (itemAtPosition) {
            0 -> {
                if (firstRun) {
                    firstRun = false
                    return
                }
                showAll()
            }
            1 -> showMovie()
            2 -> showTv()
            else -> {
                snackbarSystem.showSnackbarWarning("Something went wrong, try again?")
            }
        }
    }

    /**
     * Sorts the result from the API
     *
     * @param search : The data to sort
     * @return MediaSorted
     */
    private fun sortResult(search: Search) : MediaSorted {
        var movieArray : ArrayList<SearchItem> = ArrayList()
        var tvArray : ArrayList<SearchItem> = ArrayList()

        for(element in search){
            if(element.type == "movie") {
                movieArray.add(element)
            } else {
                tvArray.add(element)
            }
        }
        return MediaSorted(movieArray, tvArray)
    }
}