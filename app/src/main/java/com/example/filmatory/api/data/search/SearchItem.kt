package com.example.filmatory.api.data.search

data class SearchItem(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val title: String,
    val type: String
)