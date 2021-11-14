package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.search.Search
import com.example.filmatory.api.data.search.SearchItem
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.SearchScene
import com.example.filmatory.systems.ApiSystem
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel
import com.yariksoffice.lingver.Lingver

class SearchController(private val searchScene : SearchScene) : MainController(searchScene) {
    var intent: Intent = searchScene.intent
    private val title = intent.getStringExtra("title")
    private var dropdown: Spinner = searchScene.findViewById(R.id.spinner1)
    private var resultRecyclerView: RecyclerView = searchScene.findViewById(R.id.recyclerView)
    private var movieListArrayList: ArrayList<MediaModel> = ArrayList()
    private var tvListArrayList: ArrayList<MediaModel> = ArrayList()
    private var movieListAdapter = DataAdapter(searchScene, movieListArrayList)
    private var tvListAdapter = DataAdapter(searchScene, tvListArrayList)

    private inner class MediaSorted(val movieArray: ArrayList<SearchItem>, val tvArray: ArrayList<SearchItem>)

    init {
        resultRecyclerView.layoutManager = LinearLayoutManager(searchScene, LinearLayoutManager.VERTICAL, false)
        val concatAdapter = ConcatAdapter(movieListAdapter, tvListAdapter)
        resultRecyclerView.adapter = concatAdapter


        //Spinner

        ArrayAdapter.createFromResource(searchScene, R.array.media_array, android.R.layout.simple_spinner_dropdown_item).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            dropdown.adapter = adapter
        }

        //Pass the scene to the listener that implements OnItemSelectedListener
        dropdown.onItemSelectedListener = searchScene

        apiSystem.requestSearch(ApiSystem.RequestBaseOptions(null, null, ::onSearch, ::onFailure), title!!)
    }

    fun onFailure(baseError: BaseError) {
        snackbarSystem.showSnackbarWarning(baseError.message)
    }

    private fun onSearch(search: Search) {
        val mediaSorted = sortResult(search)
        for(movie in mediaSorted.movieArray) {
            movieListArrayList.add(MediaModel(DataAdapter.TYPE_SEARCH_MOVIE , movie.title, movie.overview,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movie.poster_path, movie.id))
        }

        for(tv in mediaSorted.tvArray) {
            tvListArrayList.add(MediaModel(DataAdapter.TYPE_SEARCH_TV , tv.title, tv.overview,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + tv.poster_path, tv.id))
        }

        searchScene.runOnUiThread(Runnable {
            tvListAdapter.notifyDataSetChanged()
            movieListAdapter.notifyDataSetChanged()
        })
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