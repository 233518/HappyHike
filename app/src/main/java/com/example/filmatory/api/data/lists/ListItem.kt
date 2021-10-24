package com.example.filmatory.api.data.lists

data class ListItem(
    val id: Int,
    val listid: String,
    val pictureUrl: String,
    val releaseDate: String,
    val title: String,
    val type: String
)