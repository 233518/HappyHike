package com.example.filmatory.api.data.movie

data class MoviesItem(
    val genre: List<Int>,
    val id: Int,
    val pictureUrl: String,
    val releaseDate: String,
    val title: String
)