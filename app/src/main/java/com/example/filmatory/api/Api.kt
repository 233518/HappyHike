package com.example.filmatory.api

import com.example.filmatory.BuildConfig
import com.example.filmatory.errors.Api400Error
import com.example.filmatory.errors.Api404Error
import com.example.filmatory.errors.Api503Error
import com.example.filmatory.systems.ApiSystem.PostBaseOptions
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import okhttp3.*
import java.io.IOException

class Api {
    private val client = OkHttpClient()
    private val baseUrl = "https://filmatoryeksamen.herokuapp.com/en/api"

    /**
     * Sends a GET request
     *
     * @param url The url
     * @param callback The callback to run after response
     * @param requestId The request id
     * @param requestBaseOptions The request options
     */
    fun runRequestGet(url: String, callback : OnApiRequestFinishedListener, requestId: Int, requestBaseOptions: RequestBaseOptions) {
        println("API REQUEST TO $url")
        val request = Request.Builder()
            .url(baseUrl + url)
            .build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    callback.onSuccessRequestGet(response.body()?.string(), requestId,
                        requestBaseOptions.callbackSuccess as (Any) -> Unit
                    )
                } else {
                    when(response.networkResponse()!!.code()) {
                        400 -> requestBaseOptions.callbackFailure(Api400Error(response.body()!!.string()))
                        404 -> requestBaseOptions.callbackFailure(Api404Error(response.body()!!.string()))
                        else -> {
                            requestBaseOptions.callbackFailure(Api503Error("Unexpected response from server"))
                        }
                    }
                }
                response.close();
            }
            override fun onFailure(call: Call, e: IOException) {
                requestBaseOptions.callbackFailure(Api503Error("The server is not ready to handle the request"))
            }
        })
    }

    /**
     * Sends a POST request
     *
     * @param url The url
     * @param body The body information
     * @param callback The callback to run after response
     * @param requestId The request id
     * @param postBaseOptions The request options
     */
    fun runRequestPostForm(url: String, body: FormBody, callback : OnApiRequestFinishedListener, requestId: Int, postBaseOptions: PostBaseOptions) {
        val request = Request.Builder()
            .url(baseUrl + url)
            .header("Authorization", BuildConfig.API_KEY)
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    callback.onSuccessRequestPost(response.body()?.string(), requestId, postBaseOptions.callbackSuccess as (Any) -> Unit)
                } else {
                    when(response.networkResponse()!!.code()) {
                        400 -> postBaseOptions.callbackFailure(Api400Error(response.body()!!.string()))
                        404 -> postBaseOptions.callbackFailure(Api404Error(response.body()!!.string()))
                        else -> {
                            postBaseOptions.callbackFailure(Api503Error("Unexpected response from server"))
                        }
                    }
                }
                response.close();
            }
            override fun onFailure(call: Call, e: IOException) {
                postBaseOptions.callbackFailure(Api503Error("The server is not ready to handle the request"))
            }
        })
    }
}