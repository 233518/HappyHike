package com.example.filmatory.errors

class Api500Error(
    override var message: String,
    val name: HttpStatusCodes = HttpStatusCodes.INTERNAL_SERVER,
) : BaseError(name, message)