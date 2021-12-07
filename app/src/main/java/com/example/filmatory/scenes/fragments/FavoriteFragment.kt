package com.example.filmatory.scenes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Favorites
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
class FavoriteFragment(private val scene: SuperScene, private val controller: MainController) : Fragment(R.layout.fragment_favorite) {
    private val movieFavoritesArraylist: ArrayList<MediaModel> = ArrayList()
    private val tvFavoritesArraylist: ArrayList<MediaModel> = ArrayList()
    private var allFavoritesArraylist: ArrayList<MediaModel> = ArrayList()
    private lateinit var movieAdapter: DataAdapter
    private lateinit var tvAdapter: DataAdapter
    private lateinit var favorites: Favorites

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.favorite_rv)
        val movieBtn : Button = view.findViewById(R.id.movieFavoritesBtn)
        val tvBtn : Button = view.findViewById(R.id.tvFavoritesBtn)
        val allBtn : Button = view.findViewById(R.id.allFavoritesBtn)
        allFavoritesArraylist = (tvFavoritesArraylist + movieFavoritesArraylist) as ArrayList<MediaModel>
        movieAdapter =  DataAdapter(scene, controller, requireActivity(), movieFavoritesArraylist)
        tvAdapter =  DataAdapter(scene, controller, requireActivity(), tvFavoritesArraylist)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val concatAdapter = ConcatAdapter(movieAdapter, tvAdapter)
        recyclerView.adapter = concatAdapter
        movieBtn.setOnClickListener {
            showMovieFavorites()
        }
        tvBtn.setOnClickListener {
            showTvFavorites()
        }
        allBtn.setOnClickListener {
            showFavorites(favorites)
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
            var movieMediaModel = movieFavoritesArraylist[position]
            val items = favorites.userMovieFavorites.iterator()
            while(items.hasNext()) {
                val item = items.next()
                if(item.id == movieMediaModel.itemId) items.remove()
            }
            val itemsAll = favorites.userAllFavorites.iterator()
            while(itemsAll.hasNext()) {
                val item = itemsAll.next()
                if(item.id == movieMediaModel.itemId) itemsAll.remove()
            }
            movieFavoritesArraylist.removeAt(position)
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
            var tvMediaModel = tvFavoritesArraylist[position]
            val items = favorites.userTvFavorites.iterator()
            while(items.hasNext()) {
                val item = items.next()
                if(item.id == tvMediaModel.itemId) items.remove()
            }

            val itemsAll = favorites.userAllFavorites.iterator()
            while(itemsAll.hasNext()) {
                val item = itemsAll.next()
                if(item.id == tvMediaModel.itemId) itemsAll.remove()
            }
            tvFavoritesArraylist.removeAt(position)
            tvAdapter.notifyItemRemoved(position)
        }
    }

    /**
     * Creates necessary data for showing movie favorites
     * And notifies adapter of data set change
     *
     */
    private fun showMovieFavorites() {
        movieFavoritesArraylist.clear()
        tvFavoritesArraylist.clear()
        for (item in favorites.userMovieFavorites) {
            movieFavoritesArraylist.add(
                MediaModel(
                    DataAdapter.TYPE_FAVORITES_MOVIE,
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
     * Creates necessary data for showing tv favorites
     * And notifies adapter of data set change
     *
     */
    private fun showTvFavorites() {
        movieFavoritesArraylist.clear()
        tvFavoritesArraylist.clear()
        for (item in favorites.userTvFavorites) {
            tvFavoritesArraylist.add(
                MediaModel(
                    DataAdapter.TYPE_FAVORITES_TV,
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
     * Creates necessary data for showing all favorites
     * And notifies adapter of data set change
     *
     */
    fun showFavorites(favorites: Favorites){
        movieFavoritesArraylist.clear()
        tvFavoritesArraylist.clear()
        for (item in favorites.userAllFavorites) {
            if (item.type == "tv") {
                tvFavoritesArraylist.add(
                    MediaModel(
                        DataAdapter.TYPE_FAVORITES_TV,
                        item.title,
                        item.releaseDate,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                        item.id
                    )
                )
            } else {
                movieFavoritesArraylist.add(
                    MediaModel(
                        DataAdapter.TYPE_FAVORITES_MOVIE,
                        item.title,
                        item.releaseDate,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                        item.id
                    )
                )
            }
        }
        updateDataSetChanged()
        this.favorites = favorites
    }
}