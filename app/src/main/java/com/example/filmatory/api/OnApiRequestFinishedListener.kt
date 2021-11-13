package com.example.filmatory.api

interface OnApiRequestFinishedListener {
    fun onSuccessRequestGet(result : String?, requestId: Int, function: (any : Any) -> Unit);
    fun onSuccessRequestPost(result: String?, requestId: Int, function: (any: Any) -> Unit);
}