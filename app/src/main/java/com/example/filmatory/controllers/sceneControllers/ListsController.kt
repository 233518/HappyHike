package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmatory.api.data.lists.Lists
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.ListsGui
import com.example.filmatory.scenes.activities.ListsScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.items.ListItem
import com.example.filmatory.utils.adapters.ListsAdapter

/**
 * ListsController controls everything related to the lists page
 *
 * @param listsScene The ListsScene to use
 */
class ListsController(private val listsScene: ListsScene) : MainController(listsScene) {
    private val listsGui = ListsGui(listsScene, this)
    private val listsArrayList: MutableList<ListItem> = ArrayList()
    private val listsAdapter = ListsAdapter(listsArrayList, listsScene)

    init {
        listsGui.listsRecyclerView.layoutManager = LinearLayoutManager(listsScene, LinearLayoutManager.VERTICAL, false)
        listsGui.listsRecyclerView.adapter = listsAdapter
        apiSystem.requestAllLists(RequestBaseOptions(null, null, ::listsData, ::onFailure))
    }

    /**
     * Update the gui with data from API
     *
     * @param lists The response from API
     */
    private fun listsData(lists: Lists){
        lists.forEach { item ->
            listsArrayList.add(
                ListItem(
                    item.listName,
                    item.userName,
                    "https://picsum.photos/124/189",
                    item.numberOfTvShows.toString(),
                    item.numberOfMovies.toString(),
                    item.listId
                )
            )
        }
        listsScene.runOnUiThread {
            listsAdapter.notifyDataSetChanged()
        }
    }
}