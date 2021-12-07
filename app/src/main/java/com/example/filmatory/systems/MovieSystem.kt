package com.example.filmatory.systems


import android.content.Intent

import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.SuperScene
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
class MovieSystem(private val apiSystem: ApiSystem, private val snackbarSystem: SnackbarSystem, private val scene: SuperScene) {

    /**
     * Sends request to add movie to users favorite
     *
     * @param uid The user ID
     * @param movieId The movie ID
     */
    fun addMovieToFavorites(uid : String, movieId : String){
        var params: HashMap<String, String> = HashMap()
        params["movieId"] = movieId

        apiSystem.postUserAddMovieFavorite(PostBaseOptions(null, uid, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Sends request to remove movie from users favorite
     *
     * @param uid The user ID
     * @param movieId The movie ID
     */
    fun removeMovieFromFavorites(uid : String, movieId : String){
        var params: HashMap<String, String> = HashMap()
        params["movieId"] = movieId

        apiSystem.postUserRemoveMovieFavorite(PostBaseOptions(null, uid, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Sends request to add movie to users watchlist
     *
     * @param uid The user ID
     * @param movieId The movie ID
     */
    fun addMovieToWatchlist(uid : String, movieId : String){
        var params: HashMap<String, String> = HashMap()
        params["mediaId"] = movieId
        params["mediaType"] = "movie"

        apiSystem.postUserAddWatchlist(PostBaseOptions(null, uid, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Sends request to remove movie from users watchlist
     *
     * @param uid The user ID
     * @param movieId The movie ID
     */
    fun removeMovieFromWatchlist(uid : String, movieId : String){
        var params: HashMap<String, String> = HashMap()
        params["mediaId"] = movieId
        params["mediaType"] = "movie"

        apiSystem.postUserRemoveWatchlist(PostBaseOptions(null, uid, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Sends request to add movie to specific list
     *
     * @param listId The list ID
     * @param movieId The movie ID
     */
    fun addMovieToList(listId : String, movieId : String){
        var params : HashMap<String, String> = HashMap()
        params["listId"] = listId
        params["movieId"] = movieId

        apiSystem.postListAddMovie(PostBaseOptions(null, null, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Sends request to remove movie from a specific list
     *
     * @param listId The list ID
     * @param movieId The movie ID
     */
    fun removeMovieFromList(listId: String, movieId: String){
        var params : HashMap<String, String> = HashMap()
        params["listId"] = listId
        params["movieId"] = movieId

        apiSystem.postListRemoveMovie(PostBaseOptions(null, null, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Shows snackbar message of post result
     *
     * @param string The message
     */
    private fun newPostResponse(string : String?) {
        snackbarSystem.duration = 2000
        snackbarSystem.showSnackbarSuccess(string!!)
    }

    /**
     * Runs when API fails
     *
     * @param baseError The error
     */
    private fun onFailure(baseError: BaseError) {
        snackbarSystem.showSnackbarFailure(baseError.message, ::retry, "Home")
    }

    /**
     * Sends user back to start page
     *
     */
    private fun retry() {
        val intent = Intent(scene, StartScene::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        scene.finish()
        scene.startActivity(intent)
    }
}