package com.example.filmatory.api.data.Lists

data class ListItem(
    val id: Int,
    val listid: String,
    val pictureUrl: String,
    val releaseDate: String,
    val title: String,
    val type: String
)