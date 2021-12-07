package com.example.filmatory.api

/**
 * Listener for when API gets response from server
 *
 */
interface OnApiRequestFinishedListener {
    /**
     * When the GET request was successful this will run
     *
     * @param result The result
     * @param requestId The request id
     * @param function The function to call after
     */
    fun onSuccessRequestGet(result : String?, requestId: Int, function: (any : Any) -> Unit)

    /**
     * When the POST request was successful this will run
     *
     * @param result The result
     * @param requestId The request id
     * @param function The function to call after
     */
    fun onSuccessRequestPost(result: String?, requestId: Int, function: (any: Any) -> Unit)
}