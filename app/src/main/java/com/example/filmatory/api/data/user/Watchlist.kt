package com.example.filmatory.api.data.user

data class Watchlist(
    val userAllWatched: List<UserAllWatched>,
    val userMovieWatched: List<UserMovieWatched>,
    val userTvWatched: List<UserTvWatched>
)