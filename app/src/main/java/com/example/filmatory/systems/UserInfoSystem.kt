package com.example.filmatory.systems

import com.example.filmatory.errors.BaseError
import com.example.filmatory.systems.ApiSystem.PostBaseOptions

/**
 * UserInfoSystem takes care of user info actions
 *
 * @property apiSystem The api system to use
 */
class UserInfoSystem(private val apiSystem: ApiSystem) {
    private lateinit var callbackList : (id: String, name: String) -> Unit
    private lateinit var callbackFailure : (baseError : BaseError) -> Unit
    private lateinit var callbackUsername : (message: String) -> Unit
    private lateinit var listName : String

    /**
     * Updates username
     *
     * @param uid The user ID
     * @param username The new username
     */
    fun updateUsername(uid : String, username: String, callbackUsername: (message: String) -> Unit){
        updateUsernameInDatabase(uid, username, callbackUsername)
    }

    /**
     * Creates a new list
     *
     * @param uid The user ID
     * @param listName The list name
     * @param callbackList The callback to run after list created
     * @param callbackFailure The callback to run if failure
     */
    fun createList(uid : String, listName: String, callbackList: (id: String, name: String) -> Unit, callbackFailure: (baseError : BaseError) -> Unit){
        this.callbackList = callbackList
        this.callbackFailure = callbackFailure
        this.listName = listName
        createListInDatabase(uid, listName)
    }

    /**
     * Sends request to update username in database
     *
     * @param uid The user ID
     * @param username The new username
     */
    private fun updateUsernameInDatabase(uid : String, username : String, callbackUsername: (message: String) -> Unit){
        val params: HashMap<String, String> = HashMap()
        params["username"] = username
        this.callbackUsername = callbackUsername
        apiSystem.postUserUsername(PostBaseOptions(null, uid, params, ::updatedUsername, ::onFailure))
    }

    /**
     * Sends request to create new list in database
     *
     * @param uid The user ID
     * @param listName The list name
     */
    private fun createListInDatabase(uid : String, listName: String){
        val params: HashMap<String, String> = HashMap()
        params["listName"] = listName
        apiSystem.postListCreate(PostBaseOptions(null, uid, params, ::newUserResponse, ::onFailure))
    }

    /**
     * The response from the API
     *
     * @param string Result
     */
    private fun newUserResponse(string : String?) {
        callbackList(string!!, listName)
    }

    /**
     * Shows snackbar message when updating username
     */
    private fun updatedUsername(string : String?) {
        callbackUsername(string!!)
    }

    /**
     * Runs when API fails
     *
     * @param baseError The error
     */
    private fun onFailure(baseError: BaseError) {
        callbackFailure(baseError)
    }
}