package com.example.filmatory.api.data.user

data class UserListsItem(
    val listId: String,
    val listUserId: String,
    val listname: String,
    val movies: List<Movies>,
    val tvs: List<Tvs>
)