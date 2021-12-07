package com.example.filmatory.guis

import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.Tv
import com.example.filmatory.controllers.sceneControllers.TvController
import com.example.filmatory.scenes.activities.TvScene

/**
 * TvGui contains all the gui elements for the tv page
 *
 * @property tvScene The scene to use
 * @property tvController The controller to use
 */
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

    var newReviewBtn: Button = tvScene.findViewById(R.id.new_review_btn)

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

            newReviewBtn.setOnClickListener {
                tvController.newReviewActivity()
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

            newReviewBtn.setOnClickListener {
                tvController.notLoggedin()
            }
        }
    }

    /**
     * Sets the favorite button background icon
     *
     * @param drawable The drawable to set
     */
    fun setFavoriteBtnBackground(drawable: Int) {
        tvScene.runOnUiThread {
            favoriteBtn.setBackgroundResource(drawable)
        }
    }

    /**
     * Sets the watched button background icon
     *
     * @param drawable The drawable to set
     */
    fun setWatchedBtnBackground(drawable: Int) {
        tvScene.runOnUiThread {
            watchlistBtn.setBackgroundResource(drawable)
        }
    }

    /**
     * Sets the tv information
     *
     * @param movie The movie information
     */
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