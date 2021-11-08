package com.example.filmatory.systems

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.errors.BaseError
import com.example.filmatory.errors.HttpStatusCodes
import com.example.filmatory.scenes.activities.MoviesScene
import com.example.filmatory.scenes.activities.StartScene
import com.example.filmatory.systems.ApiSystem.PostBaseOptions

/**
 * MovieSystem handles interactions with movies
 *
 * @constructor
 *
 * @param apiSystem The ApiSystem to use
 * @param snackbarSystem The snackbar system to use
 */
class MovieSystem(private val apiSystem: ApiSystem, private val snackbarSystem: SnackbarSystem, private val scene: AppCompatActivity) {

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

    fun addMovieToList(listId : String, movieId : String){
        var params : HashMap<String, String> = HashMap()
        params["listId"] = listId
        params["movieId"] = movieId

        apiSystem.postListAddMovie(PostBaseOptions(null, null, params, ::newUserResponse, ::onFailure))
    }

    fun removeMovieFromList(listId: String, movieId: String){
        var params : HashMap<String, String> = HashMap()
        params["listId"] = listId
        params["movieId"] = movieId

        apiSystem.postListRemoveMovie(PostBaseOptions(null, null, params, ::newUserResponse, ::onFailure))
    }

    private fun newUserResponse(string : String?) {
        snackbarSystem.duration = 2000
        snackbarSystem.showSnackbarSuccess(string!!)
    }
    private fun onFailure(baseError: BaseError) {
        snackbarSystem.showSnackbarFailure(baseError.message, ::retry, "Home")
    }
    private fun retry() {
        val intent = Intent(scene, StartScene::class.java)
        scene.startActivity(intent)
    }
}