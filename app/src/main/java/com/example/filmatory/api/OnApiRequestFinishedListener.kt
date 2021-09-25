package com.example.filmatory.api

interface OnApiRequestFinishedListener {
    fun onSuccessRequest(result : String?, requestId: Int?, function: (apiRespons : ApiRespons) -> Unit);
    fun onErrorRequest()
}