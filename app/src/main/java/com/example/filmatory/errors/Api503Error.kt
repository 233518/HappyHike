package com.example.filmatory.errors

data class Api503Error(
    override val message: String,
    val name: HttpStatusCodes = HttpStatusCodes.SERVICE_UNAVAILABLE,
) : BaseError(name)