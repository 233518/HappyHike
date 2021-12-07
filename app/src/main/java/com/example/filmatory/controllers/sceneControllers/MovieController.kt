package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movie
import com.example.filmatory.api.data.movie.MovieReviews
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.MovieGui
import com.example.filmatory.scenes.activities.CreateReviewScene
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.systems.FavoriteSystem
import com.example.filmatory.systems.MovieSystem
import com.example.filmatory.systems.WatchlistSystem
import com.example.filmatory.utils.items.PersonItem
import com.example.filmatory.utils.adapters.PersonRecyclerViewAdapter
import com.example.filmatory.utils.adapters.ReviewAdapter
import com.example.filmatory.utils.items.ListItem
import com.example.filmatory.utils.items.ReviewItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.ArrayList

/**
 * MovieController controls everything related to the movie page
 *
 * @param movieScene The MovieScene to use
 */
class MovieController(private val movieScene: MovieScene) : MainController(movieScene) {
    private val movieGui = MovieGui(movieScene, this)
    private var intent: Intent = movieScene.intent
    private val movieSystem = MovieSystem(apiSystem, snackbarSystem, movieScene)
    private val favoriteSystem = FavoriteSystem(movieScene, movieSystem, null)
    private val watchlistSystem = WatchlistSystem(movieScene, movieSystem, null)

    private val mId = intent.getIntExtra("movieId", 0)
    private val personsArrayList: MutableList<PersonItem> = ArrayList()
    private val personsAdapter = PersonRecyclerViewAdapter(personsArrayList, movieScene)
    private var listNameArray = arrayOf<String>()
    private val listArrayList: MutableList<ListItem> = ArrayList()

    private val reviewArrayList: MutableList<ReviewItem> = ArrayList()
    private val reviewAdapter = ReviewAdapter(reviewArrayList, movieScene)

    var movieIsWatched : Boolean  = false
        private set
    var movieIsFavorited : Boolean = false
        private set

    init {
        apiSystem.requestMovie(RequestBaseOptions(mId.toString(), null, ::getMovie, ::onFailure), languageCode)
        apiSystem.requestMovieReviews(RequestBaseOptions(mId.toString(), null, ::getReviews, ::onFailure), languageCode)

        if(isLoggedIn){
            apiSystem.requestUserFavorites(RequestBaseOptions(null, uid, ::checkIfFavorited, ::onFailure))
            apiSystem.requestUserWatchlist(RequestBaseOptions(null, uid, ::checkIfWatchlist, ::onFailure))
            apiSystem.requestUserLists(RequestBaseOptions(null, uid, ::getUserLists, ::onFailure), languageCode)
        }
        //apiSystem.requestMovieWatchProviders(mId.toString(), ::getWatchprovider)
        movieGui.personsRecyclerView.layoutManager = LinearLayoutManager(movieScene, LinearLayoutManager.HORIZONTAL, false)
        movieGui.personsRecyclerView.adapter = personsAdapter

        movieGui.reviewRecyclerView.layoutManager = LinearLayoutManager(movieScene, LinearLayoutManager.VERTICAL, false)
        movieGui.reviewRecyclerView.adapter = reviewAdapter
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
     * @param movie The response from the API
     */
    private fun getMovie(movie: Movie){
        movieGui.setMovieInfo(movie)
        movie.cast.cast.take(10).forEach { item -> personsArrayList.add(PersonItem(item.name, item.character, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.profile_path, item.id)) }
        movieScene.runOnUiThread{
            personsAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Update the gui with the reviews that belongs to the movie
     *
     * @param movieReviews : API response with the reviews
     */
    private fun getReviews(movieReviews: MovieReviews){
        movieScene.runOnUiThread {
            if(movieReviews.size != 0) movieGui.reviewHeading.visibility = View.VISIBLE
            movieReviews.forEach {
                item -> reviewArrayList.add(ReviewItem(item.author, item.avatar, item.date, item.text, item.stars, item.userId, item._id))
            }
            reviewAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Gets the current users lists, and stores them in an arraylist
     *
     * @param userLists : Users lists
     */
    private fun getUserLists(userLists: UserLists){
        if (userLists.size != 0) {
            for (item in userLists) {
                listNameArray += arrayOf(item.listname)
                listArrayList.add(
                    ListItem(item.listname, item.listUserId, "", "", "", item.listId)
                )
            }
        }
    }

    /**
     * Checks if the movie is favorited
     * If favorited, set the correct icon
     *
     * @param favorites : Users favorites
     */
    private fun checkIfFavorited(favorites: Favorites){
        movieIsFavorited = favoriteSystem.checkIfMovieFavorited(favorites, mId)
        if(!movieIsFavorited) {
            movieGui.setFavoriteBtnBackground(R.drawable.favorite_icon_border)
        } else {
            movieGui.setFavoriteBtnBackground(R.drawable.favorite_icon_filled)
        }
    }

    /**
     * Checks if the movie is in watchlist
     *
     * @param watchlist : Users watchlist
     */
    private fun checkIfWatchlist(watchlist: Watchlist){
        movieIsWatched = watchlistSystem.checkIfMovieWatchlist(watchlist, mId)
        if(!movieIsWatched) {
            movieGui.setWatchedBtnBackground(R.drawable.watchlist_icon_border)
        } else {
            movieGui.setWatchedBtnBackground(R.drawable.watchlist_icon_filled)
        }
    }

    /**
     * Runs a method which adds the movie to favorites
     * Then updates icon
     */
    fun addToFavorites(){
        movieIsFavorited = favoriteSystem.addMovieToFavorites(mId.toString())
        movieGui.setFavoriteBtnBackground(R.drawable.favorite_icon_filled)
    }

    /**
     * Runs a method which removes the movie from favorites
     * Then updates icon
     */
    fun removeFromFavorites(){
        movieIsFavorited = favoriteSystem.removeMovieFromFavorites(mId.toString())
        movieGui.setFavoriteBtnBackground(R.drawable.favorite_icon_border)
    }

    /**
     * Runs a method which adds the movie to watchlist
     * Then updates icon
     */
    fun addToWatchlist(){
        movieIsWatched = watchlistSystem.addMovieToWatchlist(mId.toString())
        movieGui.setWatchedBtnBackground(R.drawable.watchlist_icon_filled)
    }

    /**
     * Runs a method which removes the movie from watchlist
     * Then updates icon
     */
    fun removeFromWatchlist(){
        movieIsWatched = watchlistSystem.removeMovieFromWatchlist(mId.toString())
        movieGui.setWatchedBtnBackground(R.drawable.watchlist_icon_border)
    }

    /**
     * Opens a confirm dialog and fills it with the users lists
     * When positive button is pressed, run a method which adds the movie to selected list.
     *
     */
    fun addToUserList(){
        movieScene.runOnUiThread {
            var chosenList: Int = -1
            MaterialAlertDialogBuilder(movieScene)
                .setTitle(movieScene.resources.getString(R.string.mylists))
                .setNeutralButton(movieScene.resources.getString(R.string.close_btn)) { dialog, which -> }
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

    /**
     * Methods which opens the review activity
     *
     */
    fun newReviewActivity(){
        val intent = Intent(movieScene, CreateReviewScene::class.java)
        intent.putExtra("mediaId", mId)
        intent.putExtra("mediaType","movie")
        movieScene.finish()
        movieScene.startActivity(intent)
    }

    /**
     * Message for the user if they are not logged in
     *
     */
    fun notLoggedin() {
        snackbarSystem.showSnackbarWarning("You need to be logged in to use this function!")
    }

    /**
     * Gets the system that contains methods for favorites
     *
     * @return favoriteSystem
     */
    override fun getFavoriteSystem(): FavoriteSystem {
        return favoriteSystem
    }

    /**
     * Gets the system that contains methods for watchlists
     *
     * @return watchlistSystem
     */
    override fun getWatchlistSystem(): WatchlistSystem {
        return watchlistSystem
    }
}