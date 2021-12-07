package com.example.filmatory.scenes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.systems.UserInfoSystem
import com.example.filmatory.utils.items.ListItem
import com.example.filmatory.utils.adapters.ListsAdapter

class ListFragment(val scene: SuperScene, controller: MainController) : Fragment(R.layout.fragment_list) {
    private val listsArrayList: MutableList<ListItem> = ArrayList()
    private lateinit var listsAdapter : ListsAdapter
    private var userInfoSystem = UserInfoSystem(controller.apiSystem)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val createListBtn : Button = view.findViewById(R.id.newListBtn)
        val recyclerView : RecyclerView = view.findViewById(R.id.userlists_rv)
        listsAdapter = ListsAdapter(listsArrayList, requireActivity())
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = listsAdapter
        createListBtn.setOnClickListener {
            val listName : String = view.findViewById<TextView>(R.id.accinfoNewListTextField).text.toString()
            userInfoSystem.createList(scene.auth.currentUser!!.uid, listName, ::addList, ::onFailure)
        }
    }

    fun onFailure(baseError: BaseError) {

    }

    fun addList(id: String, name: String) {
        listsArrayList.add(
            ListItem(
                name,
                "",
                "https://picsum.photos/124/189",
                "0",
                "0",
                id
            )
        )
        scene.runOnUiThread {
            listsAdapter.notifyDataSetChanged()
        }
    }

    fun showUserLists(userLists: UserLists){
        for (item in userLists) {
            listsArrayList.add(
                ListItem(
                    item.listname,
                    "",
                    "https://picsum.photos/124/189",
                    item.tvs.size.toString(),
                    item.movies.size.toString(),
                    item.listId
                )
            )
        }
        scene.runOnUiThread {
            listsAdapter.notifyDataSetChanged()
        }
    }
}