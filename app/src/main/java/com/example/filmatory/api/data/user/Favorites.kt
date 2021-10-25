package com.example.filmatory.api.data.user

data class Favorites(
    val userAllFavorites: List<UserAllFavorite>,
    val userMovieFavorites: List<UserMovieFavorite>,
    val userTvFavorites: List<UserTvFavorite>
)