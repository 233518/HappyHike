package com.example.filmatory.api

import com.example.filmatory.BuildConfig
import okhttp3.*
import java.io.IOException

class Api {
    private val client = OkHttpClient()
    private val baseUrl = "https://filmatoryeksamen.herokuapp.com/en/api"

    fun runRequestGet(url: String, callback : OnApiRequestFinishedListener, requestId: Int, function: (any : Any) -> Unit) {
        val request = Request.Builder()
            .url(baseUrl + url)
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                callback.onSuccessRequestGet(response.body()?.string(), requestId, function)
                response.close();
            }
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }
        })
    }
    fun runRequestPostForm(url: String, body: FormBody, callback : OnApiRequestFinishedListener, requestId: Int, function: (any : Any) -> Unit) {
        val request = Request.Builder()
            .url(baseUrl + url)
            .header("Authorization", BuildConfig.API_KEY)
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                callback.onSuccessRequestPost(response.body()?.string(), requestId, function)
                response.close();
            }
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }
        })
    }
}