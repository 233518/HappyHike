package com.example.filmatory.systems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.StartScene
import com.example.filmatory.systems.ApiSystem.PostBaseOptions

/**
 * TvSystem handles interactions with tv-shows
 *
 * @constructor
 *
 * @param apiSystem The ApiSystem to use
 */
class TvSystem(private val apiSystem: ApiSystem, private val snackbarSystem: SnackbarSystem, private val scene: AppCompatActivity) {

    /**
     * Sends request to add tv to users favorite
     *
     * @param uid The user ID
     * @param tvId The tv ID
     */
    fun addTvToFavorites(uid : String, tvId : String){
        var params: HashMap<String, String> = HashMap()
        params["tvId"] = tvId

        apiSystem.postUserAddTvFavorite(PostBaseOptions(null, uid, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Sends request to remove tv from users favorite
     *
     * @param uid The user ID
     * @param tvId The tv ID
     */
    fun removeTvFromFavorites(uid : String, tvId : String){
        var params: HashMap<String, String> = HashMap()
        params["tvId"] = tvId

        apiSystem.postUserRemoveTvFavorite(PostBaseOptions(null, uid, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Sends request to add tv to users watchlist
     *
     * @param uid The user ID
     * @param tvId The tv ID
     */
    fun addTvToWatchlist(uid : String, tvId : String){
        var params: HashMap<String, String> = HashMap()
        params["mediaId"] = tvId
        params["mediaType"] = "tv"

        apiSystem.postUserAddWatchlist(PostBaseOptions(null, uid, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Sends request to remove movie from users watchlist
     *
     * @param uid The user ID
     * @param tvId The tv ID
     */
    fun removeTvFromWatchlist(uid : String, tvId : String){
        var params: HashMap<String, String> = HashMap()
        params["mediaId"] = tvId
        params["mediaType"] = "tv"

        apiSystem.postUserRemoveWatchlist(PostBaseOptions(null, uid, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Sends request to add tv to specific list
     *
     * @param listId The list ID
     * @param tvId The tv ID
     */
    fun addTvToList(listId : String, tvId : String){
        var params : HashMap<String, String> = HashMap()
        params["listId"] = listId
        params["tvId"] = tvId

        apiSystem.postListAddTv(PostBaseOptions(null, null, params, ::newPostResponse, ::onFailure))
    }

    /**
     * Shows snackbar message of post result
     *
     * @param string
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
        snackbarSystem.showSnackbarFailure(baseError.message!!, ::retry, "Retry")
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