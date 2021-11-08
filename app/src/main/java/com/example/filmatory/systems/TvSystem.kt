package com.example.filmatory.systems

import android.content.ContentValues
import android.content.Intent
import android.util.Log
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

    fun addTvToFavorites(uid : String, tvId : String){
        var params: HashMap<String, String> = HashMap()
        params["tvId"] = tvId

        apiSystem.postUserAddTvFavorite(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    fun removeTvFromFavorites(uid : String, tvId : String){
        var params: HashMap<String, String> = HashMap()
        params["tvId"] = tvId

        apiSystem.postUserRemoveTvFavorite(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    fun addTvToWatchlist(uid : String, tvId : String){
        var params: HashMap<String, String> = HashMap()
        params["mediaId"] = tvId
        params["mediaType"] = "tv"

        apiSystem.postUserAddWatchlist(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    fun removeTvFromWatchlist(uid : String, tvId : String){
        var params: HashMap<String, String> = HashMap()
        params["mediaId"] = tvId
        params["mediaType"] = "tv"

        apiSystem.postUserRemoveWatchlist(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    fun addTvToList(listId : String, tvId : String){
        var params : HashMap<String, String> = HashMap()
        params["listId"] = listId
        params["tvId"] = tvId

        apiSystem.postListAddTv(PostBaseOptions(null, null, params, ::newUserResponse, ::onFailure))
    }

    fun removeTvFromList(listId: String, tvId: String){
        var params : HashMap<String, String> = HashMap()
        params["listId"] = listId
        params["tvId"] = tvId

        apiSystem.postListRemoveTv(PostBaseOptions(null, null, params, ::newUserResponse, ::onFailure))
    }

    private fun newUserResponse(string : String?) {
        snackbarSystem.duration = 2000
        snackbarSystem.showSnackbarSuccess(string!!)
    }

    private fun onFailure(baseError: BaseError) {
        snackbarSystem.showSnackbarFailure(baseError.message!!, ::retry, "Retry")
    }
    private fun retry() {
        val intent = Intent(scene, StartScene::class.java)
        scene.startActivity(intent)
    }
}