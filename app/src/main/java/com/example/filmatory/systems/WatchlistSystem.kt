package com.example.filmatory.systems

import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.scenes.SuperScene

/**
 * WatchlistSystem takes care of watchlist actions
 *
 * @property superScene The scene to use
 * @property movieSystem The movie system to use
 * @property tvSystem The tv system to use
 */
class WatchlistSystem(private var superScene: SuperScene, private var movieSystem: MovieSystem?, private var tvSystem: TvSystem?) {

    /**
     * Adds a movie to the users watchlist
     *
     * @param mId The movie ID
     * @return Boolean
     */
    fun addMovieToWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            movieSystem?.addMovieToWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }

    /**
     * Removes a movie from users watchlist
     *
     * @param mId The movie ID
     * @return Boolean
     */
    fun removeMovieFromWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            movieSystem?.removeMovieFromWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }

    /**
     * Checks if movie is in watchliust
     *
     * @param watchlist The users watchlist
     * @param mId The movie ID
     * @return Boolean
     */
    fun checkIfMovieWatchlist(watchlist: Watchlist, mId: Int) : Boolean {
        for (item in watchlist.userMovieWatched) {
            if (item.id == mId) return true
        }
        return false
    }

    /**
     * Adds a tv to the users watchlist
     *
     * @param mId The tv ID
     * @return Boolean
     */
    fun addTvToWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            tvSystem?.addTvToWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }

    /**
     * Removes a tv from the users watchlist
     *
     * @param mId The tv ID
     * @return Boolean
     */
    fun removeTvFromWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            tvSystem?.removeTvFromWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }

    /**
     * Checks if tv is in watchlist
     *
     * @param watchlist The users watchlist
     * @param mId The tv ID
     * @return Boolean
     */
    fun checkIfTvWatchlist(watchlist: Watchlist, mId: Int) : Boolean {
        for (item in watchlist.userTvWatched) {
            if (item.id == mId) return true
        }
        return false
    }
}