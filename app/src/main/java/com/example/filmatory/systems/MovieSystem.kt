package com.example.filmatory.systems

import com.example.filmatory.api.data.movie.MovieFrontpage

/**
 * MovieSystem handles interactions with movies
 *
 * @constructor
 *
 * @param apiSystem The ApiSystem to use
 */
class MovieSystem(apiSystem: ApiSystem) {
    val apiSystem = apiSystem
    lateinit var movieFrontpage : MovieFrontpage
}