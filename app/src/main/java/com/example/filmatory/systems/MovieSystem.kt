package com.example.filmatory.systems

import android.content.ContentValues
import android.util.Log
import com.example.filmatory.errors.BaseError
import com.example.filmatory.systems.ApiSystem.PostBaseOptions

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
        var params: HashMap<String, String> = HashMap()
        params["movieId"] = movieId

        apiSystem.postUserAddMovieFavorite(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    fun removeMovieFromFavorites(uid : String, movieId : String){
        var params: HashMap<String, String> = HashMap()
        params["movieId"] = movieId

        apiSystem.postUserRemoveMovieFavorite(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    fun addMovieToWatchlist(uid : String, movieId : String){
        var params: HashMap<String, String> = HashMap()
        params["mediaId"] = movieId
        params["mediaType"] = "movie"

        apiSystem.postUserAddWatchlist(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    fun removeMovieFromWatchlist(uid : String, movieId : String){
        var params: HashMap<String, String> = HashMap()
        params["mediaId"] = movieId
        params["mediaType"] = "movie"

        apiSystem.postUserRemoveWatchlist(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    private fun newUserResponse(string : String?) {
        Log.d(ContentValues.TAG, "$string")
    }
    private fun onFailure(baseError: BaseError) {
    TODO("Handel error!")
    }
}