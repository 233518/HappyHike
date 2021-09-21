package com.example.filmatory.api

data class PendingReview(
    val __v: Int,
    val _id: String,
    val date: String,
    val movieId: Int,
    val stars: Int,
    val text: String,
    val tvId: Any,
    val userId: String
)