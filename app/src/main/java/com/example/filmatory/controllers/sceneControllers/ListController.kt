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
    var intent: Intent = listScene.intent
    private val listId = intent.getStringExtra("listId")
    private val listName = intent.getStringExtra("listName")
    private var listRecyclerView: RecyclerView = listScene.findViewById(R.id.listRecyclerView)
    private var movieListArrayList: ArrayList<MediaModel> = ArrayList()
    private var tvListArrayList: ArrayList<MediaModel> = ArrayList()
    private var movieListAdapter = DataAdapter(listScene, listScene, movieListArrayList)
    private var tvListAdapter = DataAdapter(listScene, listScene, tvListArrayList)
    private var userIsOwner : Boolean  = false
    private val deleteListBtn : Button = listScene.findViewById(R.id.list_delete_btn)
    private var listSystem = ListSystem(apiSystem, snackbarSystem, listScene)

    init {
        if (listId != null) {
            apiSystem.requestList(RequestBaseOptions(listId, null, ::getList, ::onFailure))
        }
        if(listScene.auth.currentUser?.uid != null){
            apiSystem.requestUser(RequestBaseOptions(null, listScene.auth.currentUser!!.uid, ::checkIfOwner, ::onFailure))
            deleteListBtn.setOnClickListener {
                if (listId != null) {
                    listSystem.deleteList(listId)
                }
                val intent = Intent(listScene, ListsScene::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                listScene.finish()
                listScene.startActivity(intent)
            }
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

    private fun checkIfOwner(user : User){
        listScene.runOnUiThread(Runnable{
            for(item in user.lists){
                if(item == listId){
                    userIsOwner = true
                    deleteListBtn.visibility = View.VISIBLE
                }
            }
        })
    }
}