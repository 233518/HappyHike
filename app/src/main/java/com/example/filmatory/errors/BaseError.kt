package com.example.filmatory.errors

open class BaseError(name: HttpStatusCodes, override var message: String) : Error() {

}