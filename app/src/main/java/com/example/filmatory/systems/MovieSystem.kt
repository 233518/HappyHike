package com.example.filmatory.systems

import android.content.ContentValues
import android.util.Log
import com.example.filmatory.api.data.movie.MovieFrontpage

/**
 * MovieSystem handles interactions with movies
 *
 * @constructor
 *
 * @param apiSystem The ApiSystem to use
 */
class MovieSystem(apiSystem: ApiSystem) {
    val apiSystem = apiSystem

    fun addMovieToFavorites(uid : String, movieId : String){
        apiSystem.postUserAddMovieFavorite(uid, movieId, ::newUserResponse)
    }

    fun removeMovieFromFavorites(uid : String, movieId : String){
        apiSystem.postUserRemoveMovieFavorite(uid, movieId, ::newUserResponse)
    }

    fun addMovieToWatchlist(uid : String, movieId : String){
        apiSystem.postUserAddWatchlist(uid, movieId, "movie", ::newUserResponse)
    }

    fun removeMovieFromWatchlist(uid : String, movieId : String){
        apiSystem.postUserRemoveWatchlist(uid, movieId, "movie", ::newUserResponse)
    }

    private fun newUserResponse(string : String?) {
        Log.d(ContentValues.TAG, "$string")
    }
}