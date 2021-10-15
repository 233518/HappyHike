package com.example.filmatory.controllers.sceneControllers

import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movie
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.MovieScene

class MovieController(movieScene: MovieScene) : MainController(movieScene) {
    val movieScene = movieScene
    var intent = movieScene.intent
    val mId = intent.getIntExtra("movieId", 0)

    init {
        apiSystem.requestMovie(mId.toString() ,::getMovie)
    }

    fun getMovie(movie: Movie){
        movieScene.runOnUiThread(Runnable {
            movieScene.findViewById<TextView>(R.id.m_title).text = movie.title
            movieScene.findViewById<TextView>(R.id.m_date).text = movie.release_date
            Glide.with(movieScene)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movie.poster_path)
                .placeholder(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(movieScene.findViewById(R.id.m_img))
            movieScene.findViewById<TextView>(R.id.m_overview).text = movie.overview
        })
    }
}