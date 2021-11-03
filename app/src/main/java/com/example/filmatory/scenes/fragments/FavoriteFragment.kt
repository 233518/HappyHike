package com.example.filmatory.scenes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.utils.items.MediaItem
import com.example.filmatory.utils.adapters.RecyclerViewAdapter
import com.example.filmatory.utils.adapters.TvRecyclerViewAdapter


class FavoriteFragment : Fragment() {
    private val movieFavoritesArraylist: MutableList<MediaItem> = ArrayList()
    private val tvFavoritesArraylist: MutableList<MediaItem> = ArrayList()
    private var allFavoritesArraylist: MutableList<MediaItem> = ArrayList()
    private lateinit var movieAdapter: RecyclerViewAdapter
    private lateinit var tvAdapter: TvRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater.inflate(R.layout.fragment_favorite, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.favorite_rv)
        allFavoritesArraylist = (tvFavoritesArraylist + movieFavoritesArraylist) as MutableList<MediaItem>
        movieAdapter =  RecyclerViewAdapter(movieFavoritesArraylist, requireActivity())
        tvAdapter =  TvRecyclerViewAdapter(tvFavoritesArraylist, requireActivity())
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val concatAdapter = ConcatAdapter(movieAdapter, tvAdapter)
        recyclerView.adapter = concatAdapter
    }

    fun showFavorites(favorites: Favorites){
        for(item in favorites.userAllFavorites){
            if(item.type == "tv"){
                tvFavoritesArraylist.add(MediaItem(item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            } else {
                movieFavoritesArraylist.add(MediaItem(item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
        }
    }
}