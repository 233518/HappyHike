package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.lists.List
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.ListScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * ListController manipulates the ListScene gui
 *
 * @property listScene The ListScene to use
 */
class ListController(private val listScene: ListScene) : MainController(listScene) {
    var intent: Intent = listScene.intent
    private val listId = intent.getStringExtra("listId")
    private val listName = intent.getStringExtra("listName")
    private var listRecyclerView: RecyclerView = listScene.findViewById(R.id.listRecyclerView)
    private var movieListArrayList: ArrayList<MediaModel> = ArrayList()
    private var tvListArrayList: ArrayList<MediaModel> = ArrayList()
    private var movieListAdapter = DataAdapter(listScene, movieListArrayList)
    private var tvListAdapter = DataAdapter(listScene, tvListArrayList)

    init {
        if (listId != null) {
            apiSystem.requestList(RequestBaseOptions(listId, null, ::getList, ::onFailure))
        }
        listRecyclerView.layoutManager = GridLayoutManager(listScene, 2)
        val concatAdapter = ConcatAdapter(movieListAdapter, tvListAdapter)
        listRecyclerView.adapter = concatAdapter
    }

    fun onFailure(baseError: BaseError) {

    }

    /**
     * Update the gui with data from API
     *
     * @param list The respons from API
     */
    private fun getList(list: List){
        listScene.runOnUiThread(Runnable {
            listScene.findViewById<TextView>(R.id.l_title).text = listName
            for (item in list) {
                if(item.type == "tv"){
                    tvListArrayList.add(MediaModel(DataAdapter.TYPE_TV ,item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
                } else {
                    movieListArrayList.add(MediaModel(DataAdapter.TYPE_MOVIE ,item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
                }
            }
            tvListAdapter.notifyDataSetChanged()
            movieListAdapter.notifyDataSetChanged()
        })
    }
}