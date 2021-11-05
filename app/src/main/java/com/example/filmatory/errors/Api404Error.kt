package com.example.filmatory.errors

class Api404Error(
    override var message: String,
    val name: HttpStatusCodes = HttpStatusCodes.NOT_FOUND,
) : BaseError(name, message)