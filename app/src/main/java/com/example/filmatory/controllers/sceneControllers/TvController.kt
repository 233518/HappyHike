package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.MovieReviews
import com.example.filmatory.api.data.tv.Tv
import com.example.filmatory.api.data.tv.TvReviews
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.guis.TvGui
import com.example.filmatory.scenes.activities.CreateReviewScene
import com.example.filmatory.scenes.activities.TvScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.systems.FavoriteSystem
import com.example.filmatory.systems.TvSystem
import com.example.filmatory.systems.WatchlistSystem
import com.example.filmatory.utils.items.PersonItem
import com.example.filmatory.utils.adapters.PersonRecyclerViewAdapter
import com.example.filmatory.utils.adapters.ReviewAdapter
import com.example.filmatory.utils.items.ListItem
import com.example.filmatory.utils.items.ReviewItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * TvController manipulates the TvScene gui
 *
 * @param tvScene The TvScene to use
 */
class TvController(private val tvScene: TvScene) : MainController(tvScene) {
    private var intent: Intent = tvScene.intent
    private val tvSystem = TvSystem(apiSystem, snackbarSystem, tvScene)
    private val favoriteSystem = FavoriteSystem(tvScene, null, tvSystem)
    private val watchlistSystem = WatchlistSystem(tvScene, null, tvSystem)
    private val tvGui = TvGui(tvScene, this)

    private val tvId = intent.getIntExtra("tvId", 0)
    private val personsArrayList: MutableList<PersonItem> = ArrayList()
    private val personsAdapter = PersonRecyclerViewAdapter(personsArrayList, tvScene)
    private var listNameArrayList = arrayOf<String>()
    private var listArrayList : MutableList<ListItem> = ArrayList()

    private val reviewArrayList: MutableList<ReviewItem> = ArrayList()
    private val reviewAdapter = ReviewAdapter(reviewArrayList, tvScene)

    var tvIsWatched : Boolean = false
        private set
    var tvIsFavorited : Boolean = false
        private set

    init {
        apiSystem.requestTV(RequestBaseOptions(tvId.toString(), null, ::getTv, ::onFailure), languageCode)
        apiSystem.requestTvReviews(RequestBaseOptions(tvId.toString(), null, ::getReviews, ::onFailure), languageCode)

        if(isLoggedIn){
            apiSystem.requestUserFavorites(RequestBaseOptions(null, uid, ::checkIfFavorited, ::onFailure))
            apiSystem.requestUserWatchlist(RequestBaseOptions(null, uid, ::checkIfWatchlist, ::onFailure))
            apiSystem.requestUserLists(RequestBaseOptions(null, uid, ::getUserLists, ::onFailure), languageCode)
        }
        tvGui.personsRecyclerView.layoutManager = LinearLayoutManager(tvScene, LinearLayoutManager.HORIZONTAL, false)
        tvGui.personsRecyclerView.adapter = personsAdapter

        tvGui.reviewRecyclerView.layoutManager = LinearLayoutManager(tvScene, LinearLayoutManager.VERTICAL, false)
        tvGui.reviewRecyclerView.adapter = reviewAdapter
        //apiSystem.requestTvWatchProviders(tvId.toString(), ::getWatchprovider)
    }

    /*private fun getWatchprovider(tvWatchProviders: TvWatchProviders){
        tvScene.runOnUiThread(Runnable {
            if(tvWatchProviders.results.NO != null){
                if (!tvWatchProviders.results.NO.flatrate.isNullOrEmpty()) {
                    for (item in tvWatchProviders.results.NO.flatrate) {
                        if (item.provider_name == "Netflix") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_netflix_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_netflix_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_hbo_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_hbo_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_viaplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_viaplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_disneyplus_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_disneyplus_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_strim_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_strim_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_googleplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_googleplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_itunes_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_itunes_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
                if (!tvWatchProviders.results.NO.rent.isNullOrEmpty()) {
                    for (item in tvWatchProviders.results.NO.rent) {
                        if (item.provider_name == "Netflix") {
                            tvScene.findViewById<View>(R.id.m_r_netflix_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_netflix_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_hbo_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_hbo_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_viaplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_viaplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_disneyplus_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_disneyplus_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_strim_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_strim_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_googleplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_googleplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_itunes_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_itunes_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
                if (!tvWatchProviders.results.NO.buy.isNullOrEmpty()) {
                    for (item in tvWatchProviders.results.NO.buy) {
                        if (item.provider_name == "Netflix") {
                            tvScene.findViewById<View>(R.id.m_b_netflix_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_netflix_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_hbo_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_hbo_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_viaplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_viaplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvSceneitemAtPosition.findViewById<View>(R.id.m_b_disneyplus_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_disneyplus_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_strim_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_strim_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_googleplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_googleplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_itunes_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_itunes_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        })
    }*/

    /**
     * Update the gui with data from API
     *
     * @param tv The response from API
     */
    private fun getTv(tv : Tv){
        tvGui.setTvInfo(tv)
        tv.personer.cast.take(10).forEach { item ->
            personsArrayList.add(
                PersonItem(
                    item.name,
                    item.character,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.profile_path,
                    item.id
                )
            )
        }
        tvScene.runOnUiThread{
            personsAdapter.notifyDataSetChanged()
        }
    }

    private fun getReviews(tvReviews: TvReviews){
        tvScene.runOnUiThread {
            if(tvReviews.size != 0) tvGui.reviewHeading.visibility = View.VISIBLE


            tvReviews.forEach {
                    item -> reviewArrayList.add(ReviewItem(item.author, item.avatar, item.date, item.text, item.stars, item.userId, item._id))
            }
            reviewAdapter.notifyDataSetChanged()
        }
    }

    private fun getUserLists(userLists: UserLists){
        if(userLists.size != 0){
            for(item in userLists){
                listNameArrayList += arrayOf(item.listname)
                listArrayList.add(ListItem(item.listname, item.listUserId, "", "", "", item.listId))
            }
        } else {
            println("User does not have any lists")
        }
    }

    private fun checkIfFavorited(favorites: Favorites){
        tvIsFavorited = favoriteSystem.checkIfTvFavorited(favorites, tvId)
        if(!tvIsFavorited) {
            tvGui.setFavoriteBtnBackground(R.drawable.favorite_icon_border)
        } else {
            tvGui.setFavoriteBtnBackground(R.drawable.favorite_icon_filled)
        }
    }

    private fun checkIfWatchlist(watchlist: Watchlist){
        tvIsFavorited = watchlistSystem.checkIfTvWatchlist(watchlist, tvId)
        if(!tvIsFavorited) {
            tvGui.setWatchedBtnBackground(R.drawable.watchlist_icon_border)
        } else {
            tvGui.setWatchedBtnBackground(R.drawable.watchlist_icon_filled)
        }
    }
    fun addToFavorites(){
        tvIsFavorited = favoriteSystem.addTvToFavorites(tvId.toString())
        tvGui.setFavoriteBtnBackground(R.drawable.favorite_icon_filled)
    }

    fun removeFromFavorites(){
        tvIsFavorited = favoriteSystem.removeTvFromFavorites(tvId.toString())
        tvGui.setFavoriteBtnBackground(R.drawable.favorite_icon_border)
    }

    fun addToWatchlist(){
        tvIsFavorited = watchlistSystem.addTvToWatchlist(tvId.toString())
        tvGui.setWatchedBtnBackground(R.drawable.watchlist_icon_filled)
    }

    fun removeFromWatchlist(){
        tvIsFavorited = watchlistSystem.removeMovieFromWatchlist(tvId.toString())
        tvGui.setWatchedBtnBackground(R.drawable.watchlist_icon_border)
    }

    fun addToUserList(){
        var chosenList : Int = -1
        MaterialAlertDialogBuilder(tvScene)
            .setTitle(tvScene.resources.getString(R.string.mylists))
            .setNeutralButton(tvScene.resources.getString(R.string.cancel_btn)) { _, which -> }
            .setPositiveButton(tvScene.resources.getString(R.string.confirm_btn)) { dialog, which ->
                if(chosenList != -1){
                    tvSystem.addTvToList(listArrayList[chosenList].list_id, tvId.toString())
                } else {
                    snackbarSystem.showSnackbarWarning("No list was selected")
                }
            }
            .setSingleChoiceItems(listNameArrayList, chosenList) { dialog, which ->
                chosenList = which
            }
            .show()
    }

    fun newReviewActivity(){
        val intent = Intent(tvScene, CreateReviewScene::class.java)
        intent.putExtra("mediaId", tvId)
        intent.putExtra("mediaType","tv")
        tvScene.finish()
        tvScene.startActivity(intent)
    }

    fun notLoggedin() {
        snackbarSystem.showSnackbarWarning("You need to log in to use this function!")
    }
}