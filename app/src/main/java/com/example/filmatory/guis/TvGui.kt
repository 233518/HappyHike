package com.example.filmatory.guis

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.Tv
import com.example.filmatory.controllers.sceneControllers.TvController
import com.example.filmatory.scenes.activities.TvScene

class TvGui(private var tvScene: TvScene, private var tvController: TvController) {
    var favoriteBtn : ImageButton = tvScene.findViewById(R.id.movie_favorite_icon)
    var watchlistBtn : ImageButton = tvScene.findViewById(R.id.movie_watchlist_icon)

    var personsRecyclerView: RecyclerView = tvScene.findViewById(R.id.m_person_slider)
    var reviewRecyclerView : RecyclerView = tvScene.findViewById(R.id.review_rv)

    var addToListBtn : TextView = tvScene.findViewById(R.id.movie_addtolist_btn)
    var tvTitle : TextView = tvScene.findViewById(R.id.m_title)
    var tvDate : TextView = tvScene.findViewById(R.id.m_date)
    var tvOverview : TextView = tvScene.findViewById(R.id.m_overview)

    var tvImage : ImageView = tvScene.findViewById(R.id.m_img)
    var reviewHeading: TextView = tvScene.findViewById(R.id.review_heading)

    init {
        if(tvController.isLoggedIn) {
            favoriteBtn.setOnClickListener {
                if (!tvController.tvIsFavorited) {
                    tvController.addToFavorites()
                } else {
                    tvController.removeFromFavorites()
                }
            }

            watchlistBtn.setOnClickListener {
                if (!tvController.tvIsWatched) {
                    tvController.addToWatchlist()
                } else {
                    tvController.removeFromWatchlist()
                }
            }

            addToListBtn.setOnClickListener {
                tvController.addToUserList()
            }
        } else {
            favoriteBtn.setOnClickListener {
               tvController.notLoggedin()
            }

            watchlistBtn.setOnClickListener {
                tvController.notLoggedin()
            }

            addToListBtn.setOnClickListener {
                tvController.notLoggedin()
            }
        }
    }

    fun setFavoriteBtnBackground(drawable: Int) {
        tvScene.runOnUiThread {
            favoriteBtn.setBackgroundResource(drawable)
        }
    }
    fun setWatchedBtnBackground(drawable: Int) {
        tvScene.runOnUiThread {
            watchlistBtn.setBackgroundResource(drawable)
        }
    }
    fun setTvInfo(tv: Tv) {
        tvScene.runOnUiThread {
            tvTitle.text = tv.serieinfo.name
            tvDate.text = tv.serieinfo.first_air_date

            Glide.with(tvScene)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + tv.serieinfo.poster_path)
                .placeholder(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(tvImage)
            tvOverview.text = tv.serieinfo.overview
        }
    }
}