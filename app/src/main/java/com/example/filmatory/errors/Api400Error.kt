package com.example.filmatory.errors

data class Api400Error(
    override var message: String,
    var name: HttpStatusCodes = HttpStatusCodes.BAD_REQUEST,
) : BaseError(name, message)