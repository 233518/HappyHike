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
    lateinit var movieFrontpage : MovieFrontpage

    fun addMovieToFavorites(uid : String, movieId : String){
        apiSystem.postUserAddFavorites(uid, movieId, ::newUserResponse)
    }

    private fun newUserResponse(string : String?) {
        Log.d(ContentValues.TAG, "$string")
    }
}