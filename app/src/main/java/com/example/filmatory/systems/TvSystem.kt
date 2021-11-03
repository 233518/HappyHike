package com.example.filmatory.systems

import android.content.ContentValues
import android.util.Log
import com.example.filmatory.api.data.movie.MovieFrontpage

/**
 * TvSystem handles interactions with tv-shows
 *
 * @constructor
 *
 * @param apiSystem The ApiSystem to use
 */
class TvSystem(apiSystem: ApiSystem) {
    val apiSystem = apiSystem

    fun addTvToFavorites(uid : String, tvId : String){
        apiSystem.postUserAddTvFavorite(uid, tvId, ::newUserResponse)
    }

    fun removeTvFromFavorites(uid : String, tvId : String){
        apiSystem.postUserRemoveTvFavorite(uid, tvId, ::newUserResponse)
    }

    fun addTvToWatchlist(uid : String, tvId : String){
        apiSystem.postUserAddWatchlist(uid, tvId, "tv", ::newUserResponse)
    }

    fun removeTvFromWatchlist(uid : String, tvId : String){
        apiSystem.postUserRemoveWatchlist(uid, tvId, "tv", ::newUserResponse)
    }

    private fun newUserResponse(string : String?) {
        Log.d(ContentValues.TAG, "$string")
    }
}