package com.example.filmatory.api.data.movie

data class MovieReviewsItem(
    val __v: Int,
    val _id: String,
    val author: String,
    val avatar: String,
    val date: String,
    val movieId: Int,
    val stars: Int,
    val text: String,
    val tvId: Any,
    val userId: String
)