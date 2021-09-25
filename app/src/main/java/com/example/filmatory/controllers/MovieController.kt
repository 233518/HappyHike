package com.example.filmatory.controllers

import android.widget.ImageView
import android.widget.TextView
import com.example.filmatory.R
import com.example.filmatory.scenes.MovieScene

class MovieController(movieScene: MovieScene) : MainController(movieScene) {
    val movieScene = movieScene

    init {
        var intent = movieScene.intent
        val mTitle = intent.getStringExtra("movieTitle")
        val mDate = intent.getStringExtra("movieDate")
        val mPoster = intent.getIntExtra("moviePoster", 0)

        movieScene.findViewById<TextView>(R.id.m_title).text = mTitle
        movieScene.findViewById<TextView>(R.id.m_date).text = mDate
        movieScene.findViewById<ImageView>(R.id.m_img).setImageResource(mPoster)
    }
}