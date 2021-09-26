package com.example.filmatory.api

interface OnApiRequestFinishedListener {
    fun onSuccessRequest(result : String?, requestId: Int?, function: (any : Any) -> Unit);
    fun onErrorRequest()
}