package com.example.filmatory.systems

import android.content.ContentValues
import android.util.Log

class UserInfoSystem(private val apiSystem: ApiSystem) {


    fun updateUsername(uid : String, username: String){
        updateUsernameInDatabase(uid, username)
    }


    private fun updateUsernameInDatabase(uid : String, username : String){
        apiSystem.postUserUsername(uid, username, ::newUserResponse)
    }
    /**
     * The respond from the API
     *
     * @param string Result
     */
    private fun newUserResponse(string : String?) {
        Log.d(ContentValues.TAG, "$string")
    }
}