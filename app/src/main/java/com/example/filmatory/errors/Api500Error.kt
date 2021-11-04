package com.example.filmatory.errors

class Api500Error(
    val name: HttpStatusCodes = HttpStatusCodes.INTERNAL_SERVER,
    override val message: String,
) : BaseError(name)