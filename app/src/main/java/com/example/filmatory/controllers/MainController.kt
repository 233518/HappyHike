package com.example.filmatory.controllers

import android.content.Intent
import com.example.filmatory.MainActivity
import com.example.filmatory.R
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.scenes.activities.StartScene
import com.example.filmatory.systems.*

/**
 * MainController implements everything that all other controllers will need
 * Every controller will extend this class
 *
 * @param scene The scene the controller will be connected to
 */
open class MainController(protected val scene : SuperScene) {
    val navSystem = NavSystem(scene)
    val snackbarSystem = SnackbarSystem(scene.findViewById(R.id.snackbar_layout))
    val apiSystem = MainActivity.apiSystem

    val languageCode = MainActivity.languageCode
    val uid = MainActivity.uid

    val isLoggedIn : Boolean = uid != null

    val discoverMovieFrontpage = MainActivity.discoverMovieFrontpage
    val discoverTvFrontpage = MainActivity.discoverTvFrontpage
    val recTvFrontPage = MainActivity.recTvFrontpage
    val recMovieFrontpage = MainActivity.recMovieFrontpage

    /**
     * On API failure, will show an error message
     *
     * @param error
     */
    fun onFailure(error : BaseError) {
        snackbarSystem.showSnackbarFailure(error.message, ::redirectHome, scene.resources.getString(R.string.nav_home))
    }

    /**
     * Redirects to the home page
     *
     */
    fun redirectHome() {
        val intent = Intent(scene, StartScene::class.java)
        scene.finish()
        scene.startActivity(intent)
    }

    /** Getters */

    open fun getFavoriteSystem() : FavoriteSystem? {
        return null
    }

    open fun getWatchlistSystem() : WatchlistSystem? {
        return null
    }

    /**
     * Notifies the watchlist adapter that movie data has changed
     *
     * @param position The position of the element that changed
     */
    open fun notifyMovieWatchlistAdapter(position: Int) {
        return
    }

    /**
     * Notifies the watchlist adapter that tv data has changed
     *
     * @param position The position of the element that changed
     */
    open fun notifyTvWatchlistAdapter(position: Int) {
        return
    }

    /**
     * Notifies the favorite adapter that movie data has changed
     *
     * @param position The position of the element that changed
     */
    open fun notifyMovieFavoriteAdapter(position: Int) {
        return
    }

    /**
     * Notifies the favorite adapater that tv data has changed
     *
     * @param position The position of the element that changed
     */
    open fun notifyTvFavoriteAdapter(position: Int) {
        return
    }
}