package com.example.filmatory.scenes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

class WatchlistFragment(private var scene: SuperScene) : Fragment(R.layout.fragment_watchlist) {
    private val movieWatchlistArraylist: ArrayList<MediaModel> = ArrayList()
    private val tvWatchlistArraylist: ArrayList<MediaModel> = ArrayList()
    private var allWatchlistArraylist: ArrayList<MediaModel> = ArrayList()
    private lateinit var movieAdapter: DataAdapter
    private lateinit var tvAdapter: DataAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.watchlist_rv)
        allWatchlistArraylist = (tvWatchlistArraylist + movieWatchlistArraylist) as ArrayList<MediaModel>
        movieAdapter = DataAdapter(scene, requireActivity(), movieWatchlistArraylist)
        tvAdapter = DataAdapter(scene, requireActivity(), tvWatchlistArraylist)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val concatAdapter = ConcatAdapter(movieAdapter, tvAdapter)
        recyclerView.adapter = concatAdapter
    }

    fun showWatchlist(watchlist: Watchlist){
        for (item in watchlist.userAllWatched) {
            if (item.type == "tv") {
                tvWatchlistArraylist.add(
                    MediaModel(
                        DataAdapter.TYPE_ACCINFO_TV,
                        item.title,
                        item.releaseDate,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                        item.id
                    )
                )
            } else {
                movieWatchlistArraylist.add(
                    MediaModel(
                        DataAdapter.TYPE_ACCINFO_MOVIE,
                        item.title,
                        item.releaseDate,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                        item.id
                    )
                )
            }
        }
        scene.runOnUiThread {
            movieAdapter.notifyDataSetChanged()
            tvAdapter.notifyDataSetChanged()
        }
    }
}