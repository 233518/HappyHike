package com.example.filmatory.systems

import android.content.ContentValues
import android.util.Log
import com.example.filmatory.errors.BaseError
import com.example.filmatory.systems.ApiSystem.PostBaseOptions

class UserInfoSystem(private val apiSystem: ApiSystem) {
    fun updateUsername(uid : String, username: String){
        updateUsernameInDatabase(uid, username)
    }

    private fun updateUsernameInDatabase(uid : String, username : String){
        var params: HashMap<String, String> = HashMap()
        params["username"] = username

        apiSystem.postUserUsername(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    /**
     * The respond from the API
     *
     * @param string Result
     */
    private fun newUserResponse(string : String?) {
        Log.d(ContentValues.TAG, "$string")
    }

    private fun onFailure(baseError: BaseError) {
        TODO("Handel error")
    }
}