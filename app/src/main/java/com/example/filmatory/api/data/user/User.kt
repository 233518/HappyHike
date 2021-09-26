package com.example.filmatory.api.data.user

data class User(
    val __v: Int,
    val _id: String,
    val administrator: Boolean,
    val avatar: String,
    val banned: Boolean,
    val email: String,
    val lists: List<String>,
    val movieFavourites: List<String>,
    val moviesWatched: List<Any>,
    val password: String,
    val tvFavourites: List<String>,
    val tvsWatched: List<String>,
    val username: String
)