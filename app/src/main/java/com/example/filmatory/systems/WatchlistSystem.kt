package com.example.filmatory.systems

import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.scenes.SuperScene

class WatchlistSystem(private var superScene: SuperScene, private var movieSystem: MovieSystem?, private var tvSystem: TvSystem?) {

    fun addMovieToWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            movieSystem?.addMovieToWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true;
    }
    fun removeMovieFromWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            movieSystem?.removeMovieFromWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true;
    }
    fun checkIfMovieWatchlist(watchlist: Watchlist, mId: Int) : Boolean {
        for (item in watchlist.userMovieWatched) {
            if (item.id == mId) return true
        }
        return false
    }
    fun addTvToWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            tvSystem?.addTvToWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true;
    }
    fun removeTvFromWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            tvSystem?.removeTvFromWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true;
    }
    fun checkIfTvWatchlist(watchlist: Watchlist, mId: Int) : Boolean {
        for (item in watchlist.userTvWatched) {
            if (item.id == mId) return true
        }
        return false
    }
}