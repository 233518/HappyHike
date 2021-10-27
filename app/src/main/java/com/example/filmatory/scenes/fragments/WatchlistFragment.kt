package com.example.filmatory.scenes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter


class WatchlistFragment : Fragment() {
    private val watchlistArraylist: MutableList<MediaItem> = ArrayList()
    lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_watchlist, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.watchlist_rv)
        adapter = RecyclerViewAdapter(watchlistArraylist, requireActivity())
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter

        return view
    }

    fun showWatchlist(watchlist: Watchlist){
        watchlist.userAllWatched.forEach {
            item -> watchlistArraylist.add(MediaItem(item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
    }
}