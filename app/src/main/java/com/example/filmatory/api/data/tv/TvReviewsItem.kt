package com.example.filmatory.api.data.tv

data class TvReviewsItem(
    val __v: Int,
    val _id: String,
    val author: String,
    val avatar: String,
    val date: String,
    val movieId: Any,
    val stars: Int,
    val text: String,
    val tvId: Int,
    val userId: String
)