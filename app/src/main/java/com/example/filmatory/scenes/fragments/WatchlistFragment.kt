package com.example.filmatory.scenes.fragments

import android.os.Bundle
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


class WatchlistFragment : Fragment() {
    private val movieWatchlistArraylist: MutableList<MediaItem> = ArrayList()
    private val tvWatchlistArraylist: MutableList<MediaItem> = ArrayList()
    private var allWatchlistArraylist: MutableList<MediaItem> = ArrayList()
    private lateinit var movieAdapter: RecyclerViewAdapter
    private lateinit var tvAdapter: TvRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_watchlist, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.watchlist_rv)
        allWatchlistArraylist = (tvWatchlistArraylist + movieWatchlistArraylist) as MutableList<MediaItem>
        movieAdapter = RecyclerViewAdapter(movieWatchlistArraylist, requireActivity())
        tvAdapter = TvRecyclerViewAdapter(tvWatchlistArraylist, requireActivity())
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val concatAdapter = ConcatAdapter(movieAdapter, tvAdapter)
        recyclerView.adapter = concatAdapter
        return view
    }

    fun showWatchlist(watchlist: Watchlist){
        requireActivity().runOnUiThread(Runnable {
            for(item in watchlist.userAllWatched){
                if(item.type == "tv"){
                    tvWatchlistArraylist.add(MediaItem(item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
                } else {
                    movieWatchlistArraylist.add(MediaItem(item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
                }
            }
        })
        tvAdapter.notifyDataSetChanged()
        movieAdapter.notifyDataSetChanged()
    }
}