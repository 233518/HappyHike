package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.lists.Lists
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.ListsScene
import com.example.filmatory.utils.items.ListItem
import com.example.filmatory.utils.adapters.ListsAdapter

/**
 * ListsController manipulates the ListsScene gui
 *
 * @param listsScene The ListsScene to use
 */
class ListsController(private val listsScene: ListsScene) : MainController(listsScene) {
    private val listsArrayList: MutableList<ListItem> = ArrayList()
    private val listsRecyclerView: RecyclerView = listsScene.findViewById(R.id.recyclerView)
    private val listsAdapter = ListsAdapter(listsArrayList, listsScene)

    init {
        listsRecyclerView.layoutManager = LinearLayoutManager(listsScene, LinearLayoutManager.VERTICAL, false)
        listsRecyclerView.adapter = listsAdapter
        apiSystem.requestAllLists(::listsData)
    }

    /**
     * Update the gui with data from API
     *
     * @param lists The response from API
     */
    private fun listsData(lists: Lists){
        listsScene.runOnUiThread(Runnable {
            lists.forEach{
                    item -> listsArrayList.add(ListItem(item.listName, item.userName, "http://placeimg.com/189/124/any", item.numberOfTvShows.toString(), item.numberOfMovies.toString(), item.listId))
            }
            listsAdapter.notifyDataSetChanged()
        })
    }
}