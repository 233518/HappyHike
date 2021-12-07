package com.example.filmatory.scenes.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * This fragments is a component to show the users watchlist in a recycler view
 *
 * @property scene : The scene to use
 * @property controller : The controller to use
 */
class WatchlistFragment(private val scene: SuperScene, private val controller: MainController) : Fragment(R.layout.fragment_watchlist) {
    private val movieWatchlistArraylist: ArrayList<MediaModel> = ArrayList()
    private val tvWatchlistArraylist: ArrayList<MediaModel> = ArrayList()
    private var allWatchlistArraylist: ArrayList<MediaModel> = ArrayList()
    private lateinit var movieAdapter: DataAdapter
    private lateinit var tvAdapter: DataAdapter
    private lateinit var watchlist : Watchlist

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.watchlist_rv)
        val movieBtn : Button = view.findViewById(R.id.movieWatchlistBtn)
        val tvBtn : Button = view.findViewById(R.id.tvWatchlistBtn)
        val allBtn : Button = view.findViewById(R.id.allWatchlistBtn)
        allWatchlistArraylist = (tvWatchlistArraylist + movieWatchlistArraylist) as ArrayList<MediaModel>
        movieAdapter = DataAdapter(scene, controller, requireActivity(), movieWatchlistArraylist)
        tvAdapter = DataAdapter(scene, controller, requireActivity(), tvWatchlistArraylist)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val concatAdapter = ConcatAdapter(movieAdapter, tvAdapter)
        recyclerView.adapter = concatAdapter
        movieBtn.setOnClickListener {
            showMovieWatchlist()
        }
        tvBtn.setOnClickListener {
            showTvWatchlist()
        }
        allBtn.setOnClickListener {
            showWatchlist(watchlist)
        }
    }

    /**
     * Notifies the movie and tv adapter that the data set changed
     *
     */
    fun updateDataSetChanged() {
        scene.runOnUiThread {
            movieAdapter.notifyDataSetChanged()
            tvAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Removes a movie from array and notifies the adapter of item removed
     *
     * @param position : The position of the element to remove
     */
    fun removeMovieItem(position: Int) {
        scene.runOnUiThread{
            var movieMediaModel = movieWatchlistArraylist[position]
            val items = watchlist.userMovieWatched.iterator()
            while(items.hasNext()) {
                val item = items.next()
                if(item.id == movieMediaModel.itemId) items.remove()
            }
            val itemsAll = watchlist.userAllWatched.iterator()
            while(itemsAll.hasNext()) {
                val item = itemsAll.next()
                if(item.id == movieMediaModel.itemId) itemsAll.remove()
            }
            movieWatchlistArraylist.removeAt(position)
            movieAdapter.notifyItemRemoved(position)
        }
    }

    /**
     * Removes a tv from array and notifies the adapter of item removed
     *
     * @param position : The position of the element to remove
     */
    fun removeTvItem(position: Int) {
        scene.runOnUiThread{
            var tvMediaModel = tvWatchlistArraylist[position]
            val items = watchlist.userTvWatched.iterator()
            while(items.hasNext()) {
                val item = items.next()
                if(item.id == tvMediaModel.itemId) items.remove()
            }
            val itemsAll = watchlist.userAllWatched.iterator()
            while(itemsAll.hasNext()) {
                val item = itemsAll.next()
                if(item.id == tvMediaModel.itemId) itemsAll.remove()
            }
            tvWatchlistArraylist.removeAt(position)
            tvAdapter.notifyItemRemoved(position)
        }
    }

    /**
     * Clears all the data and creates the movie data for the adapter from scratch
     * Notifies the adapter of data set change after
     */
    fun showMovieWatchlist() {
        movieWatchlistArraylist.clear()
        tvWatchlistArraylist.clear()
        for (item in watchlist.userMovieWatched) {
            movieWatchlistArraylist.add(
                MediaModel(
                    DataAdapter.TYPE_WATCHLIST_MOVIE,
                    item.title,
                    item.releaseDate,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                    item.id
                )
            )
        }
        updateDataSetChanged()
    }

    /**
     * Clears all the data and creates the tv data for the adapter from scratch
     * Notifies the adapter of data set change after
     */
    fun showTvWatchlist() {
        movieWatchlistArraylist.clear()
        tvWatchlistArraylist.clear()
        for (item in watchlist.userTvWatched) {
            tvWatchlistArraylist.add(
                MediaModel(
                    DataAdapter.TYPE_WATCHLIST_TV,
                    item.title,
                    item.releaseDate,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                    item.id
                )
            )
        }
        updateDataSetChanged()
    }

    /**
     * Clears all the data and creates the data for the adapter from scratch
     * Notifies the adapter of data set change after
     */
    fun showWatchlist(watchlist: Watchlist){
        movieWatchlistArraylist.clear()
        tvWatchlistArraylist.clear()
        for (item in watchlist.userAllWatched) {
            if (item.type == "tv") {
                tvWatchlistArraylist.add(
                    MediaModel(
                        DataAdapter.TYPE_WATCHLIST_TV,
                        item.title,
                        item.releaseDate,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                        item.id
                    )
                )
            } else {
                movieWatchlistArraylist.add(
                    MediaModel(
                        DataAdapter.TYPE_WATCHLIST_MOVIE,
                        item.title,
                        item.releaseDate,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                        item.id
                    )
                )
            }
        }
        updateDataSetChanged()
        this.watchlist = watchlist
    }
}