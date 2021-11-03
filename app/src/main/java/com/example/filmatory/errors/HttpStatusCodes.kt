package com.example.filmatory.errors

enum class HttpStatusCodes(val status: Int) {
    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER(500)
}