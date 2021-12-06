package com.example.filmatory.systems

import com.example.filmatory.errors.BaseError
import com.example.filmatory.systems.ApiSystem.PostBaseOptions

class UserInfoSystem(private val apiSystem: ApiSystem) {
    private lateinit var callbackList : (id: String, name: String) -> Unit
    private lateinit var callbackFailure : (baseError : BaseError) -> Unit
    private lateinit var listName : String

    fun updateUsername(uid : String, username: String){
        updateUsernameInDatabase(uid, username)
    }

    fun createList(uid : String, listName: String, callbackList: (id: String, name: String) -> Unit, callbackFailure: (baseError : BaseError) -> Unit){
        this.callbackList = callbackList
        this.callbackFailure = callbackFailure
        this.listName = listName
        createListInDatabase(uid, listName)
    }

    private fun updateUsernameInDatabase(uid : String, username : String){
        val params: HashMap<String, String> = HashMap()
        params["username"] = username
        apiSystem.postUserUsername(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    private fun createListInDatabase(uid : String, listName: String){
        val params: HashMap<String, String> = HashMap()
        params["listName"] = listName
        apiSystem.postListCreate(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    /**
     * The respond from the API
     *
     * @param string Result
     */
    private fun newUserResponse(string : String?) {
        callbackList(string!!, listName)
    }

    private fun onFailure(baseError: BaseError) {
        callbackFailure(baseError)
    }
}