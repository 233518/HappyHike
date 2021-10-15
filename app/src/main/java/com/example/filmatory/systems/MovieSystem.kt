package com.example.filmatory.systems

import com.example.filmatory.api.data.movie.MovieFrontpage

class MovieSystem(apiSystem: ApiSystem) {
    val apiSystem = apiSystem
    lateinit var movieFrontpage : MovieFrontpage
}