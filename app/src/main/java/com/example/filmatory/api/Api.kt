package com.example.filmatory.api

import okhttp3.*
import java.io.IOException

class Api {
    private val client = OkHttpClient()
    private val baseUrl = "https://filmatoryeksamen.herokuapp.com/en/api"
    private var onApiRequestFinishedListener: OnApiRequestFinishedListener? = null
    private var requestId: Int? = null

    fun runRequest(url: String, callback : OnApiRequestFinishedListener, requestId: Int, function: (apiRespons : ApiRespons) -> Unit) {
        this.onApiRequestFinishedListener = callback
        this.requestId = requestId
        val request = Request.Builder()
            .url(baseUrl + url)
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                onApiRequestFinishedListener?.onSuccessRequest(response.body()?.string(), requestId, function)
            }
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }
        })
    }
}