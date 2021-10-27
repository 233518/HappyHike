package com.example.filmatory.scenes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter
import com.example.filmatory.utils.TvRecyclerViewAdapter


class FavoriteFragment : Fragment() {
    private val favoritesArraylist: MutableList<MediaItem> = ArrayList()
    private val tvFavoritesArraylist: MutableList<MediaItem> = ArrayList()
    lateinit var movieAdapter: RecyclerViewAdapter
    lateinit var tvAdapter: TvRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater.inflate(R.layout.fragment_favorite, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.favorite_rv)
        movieAdapter =  RecyclerViewAdapter(favoritesArraylist, requireActivity())
        tvAdapter =  TvRecyclerViewAdapter(tvFavoritesArraylist, requireActivity())
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = movieAdapter
        return view
    }

    fun showFavorites(favorites: Favorites){
        favorites.userAllFavorites.forEach {
                item -> favoritesArraylist.add(MediaItem(item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
    }

    fun showTvFavorites(favorites: Favorites){
        favorites.userTvFavorites.forEach {
                item -> tvFavoritesArraylist.add(MediaItem(item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
    }
}