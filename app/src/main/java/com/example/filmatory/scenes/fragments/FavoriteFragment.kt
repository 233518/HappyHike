package com.example.filmatory.scenes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

class FavoriteFragment(private var scene: SuperScene) : Fragment(R.layout.fragment_favorite) {
    private val movieFavoritesArraylist: ArrayList<MediaModel> = ArrayList()
    private val tvFavoritesArraylist: ArrayList<MediaModel> = ArrayList()
    private var allFavoritesArraylist: ArrayList<MediaModel> = ArrayList()
    private lateinit var movieAdapter: DataAdapter
    private lateinit var tvAdapter: DataAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.favorite_rv)
        allFavoritesArraylist = (tvFavoritesArraylist + movieFavoritesArraylist) as ArrayList<MediaModel>
        movieAdapter =  DataAdapter(scene, requireActivity(), movieFavoritesArraylist)
        tvAdapter =  DataAdapter(scene, requireActivity(), tvFavoritesArraylist)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val concatAdapter = ConcatAdapter(movieAdapter, tvAdapter)
        recyclerView.adapter = concatAdapter
    }

    fun showFavorites(favorites: Favorites){
        activity?.runOnUiThread {
            if (isAdded) {
                for (item in favorites.userAllFavorites) {
                    if (item.type == "tv") {
                        tvFavoritesArraylist.add(
                            MediaModel(
                                DataAdapter.TYPE_ACCINFO_TV,
                                item.title,
                                item.releaseDate,
                                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl,
                                item.id
                            )
                        )
                    } else {
                        movieFavoritesArraylist.add(
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
                movieAdapter.notifyDataSetChanged()
                tvAdapter.notifyDataSetChanged()
            } else {
                println("Could not retrieve user favorites, not attached to activity")
            }
        }
    }
}