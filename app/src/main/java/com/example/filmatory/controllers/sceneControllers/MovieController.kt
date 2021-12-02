package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movie
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.systems.MovieSystem
import com.example.filmatory.systems.SnackbarSystem
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
    var intent: Intent = movieScene.intent
    private val movieSystem = MovieSystem(apiSystem, snackbarSystem, movieScene)
    private val mId = intent.getIntExtra("movieId", 0)
    private val personsArrayList: MutableList<PersonItem> = ArrayList()
    private val personsRecyclerView: RecyclerView = movieScene.findViewById(R.id.m_person_slider)
    private val personsAdapter = PersonRecyclerViewAdapter(personsArrayList, movieScene)
    private val favoriteBtn : ImageButton = movieScene.findViewById(R.id.movie_favorite_icon)
    private val watchlistBtn : ImageButton = movieScene.findViewById(R.id.movie_watchlist_icon)
    private val addToListBtn : TextView = movieScene.findViewById(R.id.movie_addtolist_btn)
    private var movieIsWatched : Boolean  = false
    private var movieIsFavorited : Boolean = false
    private var listNameArray = arrayOf<String>()
    private val listArrayList: MutableList<ListItem> = ArrayList()

    init {
        apiSystem.requestMovie(RequestBaseOptions(mId.toString(), null, ::getMovie, ::onFailure), languageCode)
        if(movieScene.auth.currentUser?.uid != null){
            apiSystem.requestUserFavorites(RequestBaseOptions(null, movieScene.auth.currentUser?.uid, ::checkIfFavorited, ::onFailure))
            apiSystem.requestUserWatchlist(RequestBaseOptions(null, movieScene.auth.currentUser?.uid, ::checkIfWatchlist, ::onFailure))
            apiSystem.requestUserLists(RequestBaseOptions(null, movieScene.auth.currentUser?.uid, ::getUserLists, ::onFailure), languageCode)
            favoriteBtn.setOnClickListener {
                if(!movieIsFavorited){
                    addToFavorites()
                } else{
                    removeFromFavorites()
                }
            }
            watchlistBtn.setOnClickListener {
                if(!movieIsWatched){
                    addToWatchlist()
                } else {
                    removeFromWatchlist()
                }
            }
            addToListBtn.setOnClickListener {
                addToUserList()
            }
        }
        //apiSystem.requestMovieWatchProviders(mId.toString(), ::getWatchprovider)
        personsRecyclerView.layoutManager = LinearLayoutManager(movieScene, LinearLayoutManager.HORIZONTAL, false)
        personsRecyclerView.adapter = personsAdapter
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
                                )
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
        movieScene.runOnUiThread(Runnable {
            movieScene.findViewById<TextView>(R.id.m_title).text = movie.filminfo.title
            movieScene.findViewById<TextView>(R.id.m_date).text = movie.filminfo.release_date
            Glide.with(movieScene)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movie.filminfo.poster_path)
                .placeholder(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(movieScene.findViewById(R.id.m_img))
            movieScene.findViewById<TextView>(R.id.m_overview).text = movie.filminfo.overview
            movie.cast.cast.take(10).forEach {
                    item -> personsArrayList.add(PersonItem(item.name,item.character,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.profile_path,item.id))
            }
            personsAdapter.notifyDataSetChanged()
        })
    }
    private fun addToFavorites(){
        movieScene.runOnUiThread(Runnable {
            movieSystem.addMovieToFavorites(movieScene.auth.currentUser!!.uid, mId.toString())
            movieIsFavorited = true
            favoriteBtn.setBackgroundResource(R.drawable.favorite_icon_filled)
        })
    }

    private fun removeFromFavorites(){
        movieScene.runOnUiThread(Runnable {
            movieSystem.removeMovieFromFavorites(movieScene.auth.currentUser!!.uid, mId.toString())
            movieIsFavorited = false
            favoriteBtn.setBackgroundResource(R.drawable.favorite_icon_border)
        })
    }

    private fun addToWatchlist(){
        movieScene.runOnUiThread(Runnable {
            movieSystem.addMovieToWatchlist(movieScene.auth.currentUser!!.uid, mId.toString())
            movieIsWatched = true
            watchlistBtn.setBackgroundResource(R.drawable.watchlist_icon_filled)
        })
    }

    private fun removeFromWatchlist(){
        movieScene.runOnUiThread(Runnable {
            movieSystem.removeMovieFromWatchlist(movieScene.auth.currentUser!!.uid, mId.toString())
            movieIsWatched = false
            watchlistBtn.setBackgroundResource(R.drawable.watchlist_icon_border)
        })
    }

    private fun addToUserList(){
        movieScene.runOnUiThread(Runnable {
            var chosenList: Int = -1
            MaterialAlertDialogBuilder(movieScene)
                .setTitle(movieScene.resources.getString(R.string.mylists))
                .setNeutralButton(movieScene.resources.getString(R.string.cancel_btn)) { dialog, which ->

                }
                .setPositiveButton(movieScene.resources.getString(R.string.confirm_btn)) { dialog, which ->
                    if(chosenList != -1){
                        movieSystem.addMovieToList(listArrayList[chosenList].list_id, mId.toString())
                    } else {
                        snackbarSystem.showSnackbarWarning("No list was selected")
                    }
                }
                .setSingleChoiceItems(listNameArray, chosenList) { dialog, which ->
                    chosenList = which
                }
            .show()
        })
    }

    private fun getUserLists(userLists: UserLists){
        movieScene.runOnUiThread(Runnable {
            if (userLists.size != 0) {
                for (item in userLists) {
                    listNameArray += arrayOf(item.listname)
                    listArrayList.add(
                        ListItem(item.listname, item.listUserId, "", "", "", item.listId)) }
            } else {
                println("User does not have any lists")
            }
        })
    }

    private fun checkIfFavorited(favorites: Favorites){
        movieScene.runOnUiThread(Runnable {
            for (item in favorites.userMovieFavorites) {
                if (item.id == mId){
                    movieIsFavorited = true
                    favoriteBtn.setBackgroundResource(R.drawable.favorite_icon_filled)
                }
            }
            if(!movieIsFavorited){
                movieIsFavorited = false
                favoriteBtn.setBackgroundResource(R.drawable.favorite_icon_border)
            }
        })
    }

    private fun checkIfWatchlist(watchlist: Watchlist){
        movieScene.runOnUiThread(Runnable {
            for(item in watchlist.userMovieWatched){
                if(item.id == mId){
                    movieIsWatched = true
                    watchlistBtn.setBackgroundResource(R.drawable.watchlist_icon_filled)
                }
            }
            if(!movieIsWatched) {
                movieIsWatched = false
                watchlistBtn.setBackgroundResource(R.drawable.watchlist_icon_border)
            }
        })
    }
}