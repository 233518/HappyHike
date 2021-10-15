package com.example.filmatory.api.data.movie

data class UpcomingMoviesItem(
    val id: Int,
    val pictureUrl: String,
    val releaseDate: String,
    val title: String
)