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

class SearchController(private val searchScene : SearchScene) : MainController(searchScene) {
    private val searchGui = SearchGui(searchScene, this)
    private val intent: Intent = searchScene.intent
    private val title = intent.getStringExtra("title")

    private var movieListArrayList: ArrayList<MediaModel> = ArrayList()
    private var tvListArrayList: ArrayList<MediaModel> = ArrayList()
    private var movieListAdapter = DataAdapter(searchScene, searchScene, movieListArrayList)
    private var tvListAdapter = DataAdapter(searchScene, searchScene, tvListArrayList)

    private inner class MediaSorted(val movieArray: ArrayList<SearchItem>, val tvArray: ArrayList<SearchItem>)

    init {
        searchGui.resultRecyclerView.layoutManager = LinearLayoutManager(searchScene, LinearLayoutManager.VERTICAL, false)
        val concatAdapter = ConcatAdapter(movieListAdapter, tvListAdapter)
        searchGui.resultRecyclerView.adapter = concatAdapter

        apiSystem.requestSearch(ApiSystem.RequestBaseOptions(null, null, ::onSearch, ::onFailure), title!!, languageCode)
    }

    private fun onSearch(search: Search) {
        val mediaSorted = sortResult(search)
        for(movie in mediaSorted.movieArray) {
            movieListArrayList.add(MediaModel(DataAdapter.TYPE_SEARCH_MOVIE , movie.title, movie.overview,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movie.poster_path, movie.id))
        }

        for(tv in mediaSorted.tvArray) {
            tvListArrayList.add(MediaModel(DataAdapter.TYPE_SEARCH_TV , tv.title, tv.overview,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + tv.poster_path, tv.id))
        }

        searchScene.runOnUiThread {
            tvListAdapter.notifyDataSetChanged()
            movieListAdapter.notifyDataSetChanged()
        }
    }

    fun onNewSelected(itemAtPosition: Any) {
        //TODO: MAKE ADAPTER UPDATE
    }

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