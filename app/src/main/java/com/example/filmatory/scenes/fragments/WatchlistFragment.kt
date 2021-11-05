package com.example.filmatory.scenes.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.utils.items.MediaItem
import com.example.filmatory.utils.adapters.RecyclerViewAdapter
import com.example.filmatory.utils.adapters.TvRecyclerViewAdapter


class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {
    private val movieWatchlistArraylist: MutableList<MediaItem> = ArrayList()
    private val tvWatchlistArraylist: MutableList<MediaItem> = ArrayList()
    private var allWatchlistArraylist: MutableList<MediaItem> = ArrayList()
    private lateinit var movieAdapter: RecyclerViewAdapter
    private lateinit var tvAdapter: TvRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.watchlist_rv)
        allWatchlistArraylist = (tvWatchlistArraylist + movieWatchlistArraylist) as MutableList<MediaItem>
        movieAdapter = RecyclerViewAdapter(movieWatchlistArraylist, requireActivity())
        tvAdapter = TvRecyclerViewAdapter(tvWatchlistArraylist, requireActivity())
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val concatAdapter = ConcatAdapter(movieAdapter, tvAdapter)
        recyclerView.adapter = concatAdapter
    }

    fun showWatchlist(watchlist: Watchlist){
        activity?.runOnUiThread(Runnable {
            if(isAdded){
                for(item in watchlist.userAllWatched){
                    if(item.type == "tv"){
                        tvWatchlistArraylist.add(MediaItem(item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
                    } else {
                        movieWatchlistArraylist.add(MediaItem(item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
                    }
                }
                movieAdapter.notifyDataSetChanged()
                tvAdapter.notifyDataSetChanged()
            } else {
                println("Could not retrieve user watchlist, not attached to activity")
            }
        })
    }
}