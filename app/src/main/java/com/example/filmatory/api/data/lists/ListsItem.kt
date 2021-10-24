package com.example.filmatory.api.data.lists

data class ListsItem(
    val listId: String,
    val listName: String,
    val numberOfMovies: Int,
    val numberOfTvShows: Int,
    //val posters: List<String>,
    val userName: String
)