package com.example.filmatory.api.data.tv

data class TvsItem(
    val genre: List<Int>,
    val id: Int,
    val pictureUrl: String,
    val releaseDate: String,
    val title: String
)