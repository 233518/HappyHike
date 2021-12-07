package com.example.filmatory.errors

enum class HttpStatusCodes(val status: Int) {
    BAD_REQUEST(400),
    NOT_FOUND(404),
    SERVICE_UNAVAILABLE(503)
}