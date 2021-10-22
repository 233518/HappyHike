package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.Lists.Lists
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.ListsScene
import com.example.filmatory.utils.ListItem
import com.example.filmatory.utils.ListsAdapter

class ListsController(listsScene: ListsScene) : MainController(listsScene) {
    val listsScene = listsScene
    val listsArrayList: MutableList<ListItem> = ArrayList()
    val listsRecyclerView: RecyclerView = listsScene.findViewById(R.id.recyclerView)
    val listsAdapter = ListsAdapter(listsArrayList, listsScene)

    init {
        listsRecyclerView.layoutManager = LinearLayoutManager(listsScene, LinearLayoutManager.VERTICAL, false)
        listsRecyclerView.adapter = listsAdapter
        apiSystem.requestAllLists(::listsData)
    }
    fun listsData(lists: Lists){
        listsScene.runOnUiThread(Runnable {
            lists.forEach{
                    item -> listsArrayList.add(ListItem(item.listName, item.userName, "http://placeimg.com/640/480/any", item.numberOfTvShows.toString(), item.numberOfMovies.toString(), item.listId))
            }
            listsAdapter.notifyDataSetChanged()
        })
    }
}