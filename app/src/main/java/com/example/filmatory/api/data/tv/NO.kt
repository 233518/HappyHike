package com.example.filmatory.api.data.tv

data class NO(
    val ads: List<Ad>,
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String,
    val rent: List<Rent>
)