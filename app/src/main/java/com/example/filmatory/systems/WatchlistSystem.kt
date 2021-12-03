package com.example.filmatory.systems

import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.scenes.SuperScene

class WatchlistSystem(private var superScene: SuperScene, private var movieSystem: MovieSystem) {

    fun addToWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            movieSystem.addMovieToWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true;
    }
    fun removeFromWatchlist(mId: String) : Boolean{
        superScene.runOnUiThread {
            movieSystem.removeMovieFromWatchlist(superScene.auth.currentUser!!.uid, mId)
        }
        return true;
    }
    fun checkIfWatchlist(watchlist: Watchlist, mId: Int) : Boolean {
        for (item in watchlist.userMovieWatched) {
            if (item.id == mId) return true
        }
        return false
    }
}