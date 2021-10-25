package com.example.filmatory.api.data.user

data class UserMovieWatched(
    val id: Int,
    val pictureUrl: String,
    val releaseDate: String,
    val title: String,
    val type: String
)