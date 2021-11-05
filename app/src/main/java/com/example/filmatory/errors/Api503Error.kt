package com.example.filmatory.errors

data class Api503Error(
    override var message: String,
    val name: HttpStatusCodes = HttpStatusCodes.SERVICE_UNAVAILABLE,
) : BaseError(name, message)