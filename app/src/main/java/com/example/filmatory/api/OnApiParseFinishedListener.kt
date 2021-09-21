package com.example.filmatory.api

interface OnApiParseFinishedListener {
    fun onSuccessParse(serviceClass: Class<out Any?>)
}