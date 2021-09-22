package com.example.filmatory.api

import okhttp3.*
import java.io.IOException

class Api : Callback {
    private val client = OkHttpClient()
    private val baseUrl = "https://filmatoryeksamen.herokuapp.com/en/api"
    private var onApiRequestFinishedListener: OnApiRequestFinishedListener? = null
    private var requestId: Int? = null

    fun runRequest(url: String, callback : OnApiRequestFinishedListener, requestId: Int) {
        this.onApiRequestFinishedListener = callback
        this.requestId = requestId
        val request = Request.Builder()
            .url(baseUrl + url)
            .build()

        client.newCall(request).enqueue(this)
    }

    override fun onFailure(call: Call, e: IOException) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call, response: Response) {
        onApiRequestFinishedListener?.onSuccessRequest(response.body()?.string(), requestId)
    }
}