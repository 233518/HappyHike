package com.example.filmatory.api

data class DeniedReview(
    val __v: Int,
    val _id: String,
    val date: String,
    val feedback: String,
    val movieId: Int,
    val stars: Int,
    val text: String,
    val tvId: Any,
    val userId: String
)