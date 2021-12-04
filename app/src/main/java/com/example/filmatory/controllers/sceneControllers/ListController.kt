package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.lists.List
import com.example.filmatory.api.data.user.User
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.guis.ListGui
import com.example.filmatory.scenes.activities.ListScene
import com.example.filmatory.scenes.activities.ListsScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.systems.ListSystem
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * ListController manipulates the ListScene gui
 *
 * @property listScene The ListScene to use
 */
class ListController(private val listScene: ListScene) : MainController(listScene) {
    val listSystem = ListSystem(apiSystem, snackbarSystem, listScene)
    val listGui = ListGui(listScene, this)

    private val intent: Intent = listScene.intent
    private val listName = intent.getStringExtra("listName")

    private var movieListArrayList: ArrayList<MediaModel> = ArrayList()
    private var tvListArrayList: ArrayList<MediaModel> = ArrayList()
    private var movieListAdapter = DataAdapter(listScene, listScene, movieListArrayList)
    private var tvListAdapter = DataAdapter(listScene, listScene, tvListArrayList)
    private var userIsOwner : Boolean  = false

    val listId = intent.getStringExtra("listId")

    init {
        val concatAdapter = ConcatAdapter(movieListAdapter, tvListAdapter)

        if (listId != null) apiSystem.requestList(RequestBaseOptions(listId, null, ::getList, ::onFailure))
        if(isLoggedIn) apiSystem.requestUser(RequestBaseOptions(null, listScene.auth.currentUser!!.uid, ::checkIfOwner, ::onFailure))

        listGui.listRecyclerView.layoutManager = GridLayoutManager(listScene, 2)
        listGui.listRecyclerView.adapter = concatAdapter
    }

    /**
     * Update the gui with data from API
     *
     * @param list The respons from API
     */
    private fun getList(list: List){
        for (item in list) {
            if (item.type == "tv") {
                tvListArrayList.add(
                    MediaModel(
                        DataAdapter.TYPE_TV,
                        item.title,
                        item.releaseDate,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                        item.id
                    )
                )
            } else {
                movieListArrayList.add(
                    MediaModel(
                        DataAdapter.TYPE_MOVIE,
                        item.title,
                        item.releaseDate,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                        item.id
                    )
                )
            }
        }
        listScene.runOnUiThread {
            listGui.listTitle.text = listName
            tvListAdapter.notifyDataSetChanged()
            movieListAdapter.notifyDataSetChanged()
        }
    }

    private fun checkIfOwner(user : User){
        listScene.runOnUiThread {
            for (item in user.lists) {
                if (item == listId) {
                    userIsOwner = true
                    listGui.deleteListBtn.visibility = View.VISIBLE
                }
            }
        }
    }
}