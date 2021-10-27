package com.example.filmatory.scenes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.utils.ListItem
import com.example.filmatory.utils.ListsAdapter
import com.example.filmatory.utils.RecyclerViewAdapter

class ListFragment : Fragment() {
    private val listsArrayList: MutableList<ListItem> = ArrayList()
    private lateinit var listsAdapter : ListsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.userlists_rv)
        listsAdapter = ListsAdapter(listsArrayList, requireActivity())
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = listsAdapter
        return view
    }

    fun showUserLists(userLists: UserLists){
        userLists.forEach{
                item -> listsArrayList.add(ListItem(item.name, "item.userId", "http://placeimg.com/640/480/any", "19", "42", "item._id"))
        }
    }
}