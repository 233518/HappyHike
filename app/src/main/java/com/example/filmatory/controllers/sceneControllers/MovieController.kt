package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movie
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.guis.MovieGui
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.systems.FavoriteSystem
import com.example.filmatory.systems.MovieSystem
import com.example.filmatory.systems.WatchlistSystem
import com.example.filmatory.utils.items.PersonItem
import com.example.filmatory.utils.adapters.PersonRecyclerViewAdapter
import com.example.filmatory.utils.items.ListItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.ArrayList


/**
 * MovieController manipulates the MovieScene gui
 *
 * @param movieScene The MovieScene to use
 */
class MovieController(private val movieScene: MovieScene) : MainController(movieScene) {
    private var intent: Intent = movieScene.intent
    private val movieSystem = MovieSystem(apiSystem, snackbarSystem, movieScene)
    private val favoriteSystem = FavoriteSystem(movieScene, movieSystem, null)
    private val watchlistSystem = WatchlistSystem(movieScene, movieSystem)

    private val mId = intent.getIntExtra("movieId", 0)
    private val personsArrayList: MutableList<PersonItem> = ArrayList()
    private val personsAdapter = PersonRecyclerViewAdapter(personsArrayList, movieScene)
    private var listNameArray = arrayOf<String>()
    private val listArrayList: MutableList<ListItem> = ArrayList()

    var movieIsWatched : Boolean  = false
        private set
    var movieIsFavorited : Boolean = false
        private set

    private val movieGui = MovieGui(movieScene, this)

    init {
        apiSystem.requestMovie(RequestBaseOptions(mId.toString(), null, ::getMovie, ::onFailure), languageCode)

        if(movieScene.auth.currentUser?.uid != null){
            apiSystem.requestUserFavorites(RequestBaseOptions(null, movieScene.auth.currentUser?.uid, ::checkIfFavorited, ::onFailure))
            apiSystem.requestUserWatchlist(RequestBaseOptions(null, movieScene.auth.currentUser?.uid, ::checkIfWatchlist, ::onFailure))
            apiSystem.requestUserLists(RequestBaseOptions(null, movieScene.auth.currentUser?.uid, ::getUserLists, ::onFailure), languageCode)
        }
        //apiSystem.requestMovieWatchProviders(mId.toString(), ::getWatchprovider)
        movieGui.personsRecyclerView.layoutManager = LinearLayoutManager(movieScene, LinearLayoutManager.HORIZONTAL, false)
        movieGui.personsRecyclerView.adapter = personsAdapter
    }

    fun onFailure(baseError: BaseError) {

    }

    /*private fun getWatchprovider(movieWatchProviders: MovieWatchProviders){
        movieScene.runOnUiThread(Runnable {
            if(movieWatchProviders.results.NO != null){
                if (!movieWatchProviders.results.NO.flatrate.isNullOrEmpty()) {
                    for (item in movieWatchProviders.results.NO.flatrate) {
                        if (item.provider_name == "Netflix") {
                            movieScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_netflix_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_netflix_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            movieScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_hbo_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_hbo_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            movieScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_viaplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_viaplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            movieScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_disneyplus_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_disneyplus_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            movieScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_strim_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_strim_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            movieScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_googleplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_googleplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            movieScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_itunes_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_itunes_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
                if (!movieWatchProviders.results.NO.rent.isNullOrEmpty()) {
                    for (item in movieWatchProviders.results.NO.rent) {
                        if (item.provider_name == "Netflix") {
                            movieScene.findViewById<View>(R.id.m_r_netflix_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_netflix_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            movieScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_hbo_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_hbo_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            movieScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_viaplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_viaplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            movieScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_disneyplus_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_disneyplus_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            movieScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_strim_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_strim_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            movieScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_googleplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_googleplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            movieScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_itunes_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_itunes_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
                if (!movieWatchProviders.results.NO.buy.isNullOrEmpty()) {
                    for (item in movieWatchProviders.results.NO.buy) {
                        if (item.provider_name == "Netflix") {
                            movieScene.findViewById<View>(R.id.m_b_netflix_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_netflix_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )MovieScene
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            movieScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_hbo_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_hbo_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            movieScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_viaplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_viaplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            movieScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_disneyplus_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_disneyplus_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            movieScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_strim_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_strim_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            movieScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_googleplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_googleplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            movieScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_itunes_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_itunes_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(movieWatchProviders.results.NO.link)
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
     * @param movie The response from API
     */
    private fun getMovie(movie: Movie){
        movieGui.setMovieInfo(movie)
        movie.cast.cast.take(10).forEach { item ->
            personsArrayList.add(
                PersonItem(
                    item.name,
                    item.character,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.profile_path,
                    item.id
                )
            )
        }
        movieScene.runOnUiThread{
            personsAdapter.notifyDataSetChanged()
        }
    }
    fun addToFavorites(){
        movieIsFavorited = favoriteSystem.addMovieToFavorites(mId.toString())
        movieGui.setFavoriteBtnBackground(R.drawable.favorite_icon_filled)
    }

    fun removeFromFavorites(){
        movieIsFavorited = favoriteSystem.removeMovieFromFavorites(mId.toString())
        movieGui.setFavoriteBtnBackground(R.drawable.favorite_icon_border)
    }

    fun addToWatchlist(){
        movieIsWatched = watchlistSystem.addToWatchlist(mId.toString())
        movieGui.setWatchedBtnBackground(R.drawable.watchlist_icon_filled)
    }

    fun removeFromWatchlist(){
        movieIsWatched = watchlistSystem.removeFromWatchlist(mId.toString())
        movieGui.setWatchedBtnBackground(R.drawable.watchlist_icon_border)
    }

    fun addToUserList(){
        movieScene.runOnUiThread {
            var chosenList: Int = -1
            MaterialAlertDialogBuilder(movieScene)
                .setTitle(movieScene.resources.getString(R.string.mylists))
                .setNeutralButton(movieScene.resources.getString(R.string.cancel_btn)) { dialog, which ->

                }
                .setPositiveButton(movieScene.resources.getString(R.string.confirm_btn)) { dialog, which ->
                    if (chosenList != -1) {
                        movieSystem.addMovieToList(
                            listArrayList[chosenList].list_id,
                            mId.toString()
                        )
                    } else {
                        snackbarSystem.showSnackbarWarning("No list was selected")
                    }
                }
                .setSingleChoiceItems(listNameArray, chosenList) { dialog, which ->
                    chosenList = which
                }
                .show()
        }
    }

    private fun getUserLists(userLists: UserLists){
        movieScene.runOnUiThread {
            if (userLists.size != 0) {
                for (item in userLists) {
                    listNameArray += arrayOf(item.listname)
                    listArrayList.add(
                        ListItem(item.listname, item.listUserId, "", "", "", item.listId)
                    )
                }
            } else {
                println("User does not have any lists")
            }
        }
    }

    private fun checkIfFavorited(favorites: Favorites){
        movieIsFavorited = favoriteSystem.checkIfMovieFavorited(favorites, mId)
        if(!movieIsFavorited) {
            movieGui.setFavoriteBtnBackground(R.drawable.favorite_icon_border)
        } else {
            movieGui.setFavoriteBtnBackground(R.drawable.favorite_icon_filled)
        }
    }

    private fun checkIfWatchlist(watchlist: Watchlist){
        movieIsWatched = watchlistSystem.checkIfWatchlist(watchlist, mId)
        if(!movieIsWatched) {
            movieGui.setWatchedBtnBackground(R.drawable.watchlist_icon_border)
        } else {
            movieGui.setWatchedBtnBackground(R.drawable.watchlist_icon_filled)
        }
    }
}