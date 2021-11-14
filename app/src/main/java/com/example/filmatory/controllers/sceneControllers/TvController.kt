package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.Tv
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.TvScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.systems.TvSystem
import com.example.filmatory.utils.items.PersonItem
import com.example.filmatory.utils.adapters.PersonRecyclerViewAdapter
import com.example.filmatory.utils.items.ListItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * TvController manipulates the TvScene gui
 *
 * @param tvScene The TvScene to use
 */
class TvController(private val tvScene: TvScene) : MainController(tvScene) {
    var intent: Intent = tvScene.intent
    private val tvSystem = TvSystem(apiSystem, snackbarSystem, tvScene)
    private val tvId = intent.getIntExtra("tvId", 0)
    private val personsArrayList: MutableList<PersonItem> = ArrayList()
    private val personsRecyclerView: RecyclerView = tvScene.findViewById(R.id.m_person_slider)
    private val personsAdapter = PersonRecyclerViewAdapter(personsArrayList, tvScene)
    private val favoriteBtn : ImageButton = tvScene.findViewById(R.id.movie_favorite_icon)
    private val watchlistBtn : ImageButton = tvScene.findViewById(R.id.movie_watchlist_icon)
    private val addToListBtn : TextView = tvScene.findViewById(R.id.movie_addtolist_btn)
    private var tvIsWatched : Boolean = false
    private var tvIsFavorited : Boolean = false
    private var listNameArrayList = arrayOf<String>()
    private var listArrayList : MutableList<ListItem> = ArrayList()

    init {
        apiSystem.requestTV(RequestBaseOptions(tvId.toString(), null, ::getTv, ::onFailure), languageCode)
        if(tvScene.auth.currentUser?.uid != null){
            apiSystem.requestUserFavorites(RequestBaseOptions(null, tvScene.auth.currentUser?.uid, ::checkIfFavorited, ::onFailure))
            apiSystem.requestUserWatchlist(RequestBaseOptions(null, tvScene.auth.currentUser?.uid, ::checkIfWatchlist, ::onFailure))
            apiSystem.requestUserLists(RequestBaseOptions(null, tvScene.auth.currentUser?.uid, ::getUserLists, ::onFailure), languageCode)
            favoriteBtn.setOnClickListener {
                if(!tvIsFavorited){
                    addToFavorites()
                } else {
                    removeFromFavorites()
                }
            }

            watchlistBtn.setOnClickListener {
                if(!tvIsWatched){
                    addToWatchlist()
                } else {
                    removeFromWatchlist()
                }
            }

            addToListBtn.setOnClickListener {
                addToUserList()
            }
        }
        personsRecyclerView.layoutManager = LinearLayoutManager(tvScene, LinearLayoutManager.HORIZONTAL, false)
        personsRecyclerView.adapter = personsAdapter
        //apiSystem.requestTvWatchProviders(tvId.toString(), ::getWatchprovider)
    }

    fun onFailure(baseError: BaseError) {

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
                            tvScene.findViewById<View>(R.id.m_b_disneyplus_logo).visibility =
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
        tvScene.runOnUiThread(Runnable {
            tvScene.findViewById<TextView>(R.id.m_title).text = tv.serieinfo.name
            tvScene.findViewById<TextView>(R.id.m_date).text = tv.serieinfo.first_air_date
            Glide.with(tvScene)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + tv.serieinfo.poster_path)
                .placeholder(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(tvScene.findViewById(R.id.m_img))
            tvScene.findViewById<TextView>(R.id.m_overview).text = tv.serieinfo.overview

            for (item in tv.personer.cast.take(10)) {
                personsArrayList.add(PersonItem(item.name,item.character,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.profile_path,item.id))

            }
            personsAdapter.notifyDataSetChanged()
        })
    }
    private fun addToFavorites(){
        tvScene.runOnUiThread(Runnable {
            tvSystem.addTvToFavorites(tvScene.auth.currentUser!!.uid, tvId.toString())
            tvIsFavorited = true
            favoriteBtn.setBackgroundResource(R.drawable.favorite_icon_filled)
        })
    }

    private fun removeFromFavorites(){
        tvScene.runOnUiThread(Runnable {
            tvSystem.removeTvFromFavorites(tvScene.auth.currentUser!!.uid, tvId.toString())
            tvIsFavorited = false
            favoriteBtn.setBackgroundResource(R.drawable.favorite_icon_border)
        })
    }

    private fun addToWatchlist(){
        tvScene.runOnUiThread(Runnable {
            tvSystem.addTvToWatchlist(tvScene.auth.currentUser!!.uid, tvId.toString())
            tvIsWatched = true
            watchlistBtn.setBackgroundResource(R.drawable.watchlist_icon_filled)
        })
    }

    private fun removeFromWatchlist(){
        tvScene.runOnUiThread(Runnable {
            tvSystem.removeTvFromWatchlist(tvScene.auth.currentUser!!.uid, tvId.toString())
            tvIsWatched = false
            watchlistBtn.setBackgroundResource(R.drawable.watchlist_icon_border)
        })
    }

    private fun addToUserList(){
        var chosenList : Int = -1
        MaterialAlertDialogBuilder(tvScene)
            .setTitle(tvScene.resources.getString(R.string.mylists))
            .setNeutralButton(tvScene.resources.getString(R.string.cancel_btn)) { dialog, which ->

            }
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
        tvScene.runOnUiThread(Runnable {
            for (item in favorites.userTvFavorites) {
                if (item.id == tvId){
                    tvIsFavorited = true
                    favoriteBtn.setBackgroundResource(R.drawable.favorite_icon_filled)
                }
            }
            if(!tvIsFavorited){
                tvIsFavorited = false
                favoriteBtn.setBackgroundResource(R.drawable.favorite_icon_border)
            }
        })
    }

    private fun checkIfWatchlist(watchlist: Watchlist){
        tvScene.runOnUiThread(Runnable {
            for(item in watchlist.userTvWatched){
                if(item.id == tvId){
                    tvIsWatched = true
                    watchlistBtn.setBackgroundResource(R.drawable.watchlist_icon_filled)
                }
            }
            if(!tvIsWatched){
                tvIsWatched = false
                watchlistBtn.setBackgroundResource(R.drawable.watchlist_icon_border)
            }
        })
    }
}