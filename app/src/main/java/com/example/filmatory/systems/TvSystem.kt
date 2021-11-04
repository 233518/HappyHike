package com.example.filmatory.systems

import android.content.ContentValues
import android.util.Log
import com.example.filmatory.errors.BaseError
import com.example.filmatory.systems.ApiSystem.PostBaseOptions

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

    private fun newUserResponse(string : String?) {
        Log.d(ContentValues.TAG, "$string")
    }

    private fun onFailure(baseError: BaseError) {
        TODO("Handel error")
    }
}