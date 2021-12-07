package com.example.filmatory.api.data.user

data class Favorites(
    val userAllFavorites: ArrayList<UserAllFavorite>,
    val userMovieFavorites: ArrayList<UserMovieFavorite>,
    val userTvFavorites: ArrayList<UserTvFavorite>
)