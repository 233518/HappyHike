package com.example.filmatory.errors

data class Api400Error(
    override val message: String,
    val name: HttpStatusCodes = HttpStatusCodes.BAD_REQUEST,
) : BaseError(name)