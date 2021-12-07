package com.example.filmatory.api.data.user

data class Watchlist(
    val userAllWatched: ArrayList<UserAllWatched>,
    val userMovieWatched: ArrayList<UserMovieWatched>,
    val userTvWatched: ArrayList<UserTvWatched>
)