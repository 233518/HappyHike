package com.example.filmatory.api.data.user

data class UserListsItem(
    val __v: Int,
    val _id: String,
    val movies: List<String>,
    val name: String,
    val tvs: List<String>,
    val userId: String
)