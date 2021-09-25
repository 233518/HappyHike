package com.example.filmatory.controllers

import android.widget.ImageView
import android.widget.TextView
import com.example.filmatory.R
import com.example.filmatory.scenes.TvScene

class TvController(tvScene: TvScene) : MainController(tvScene) {
    val tvScene = tvScene

    init {
        var intent = tvScene.intent
        val mTitle = intent.getStringExtra("movieTitle")
        val mDate = intent.getStringExtra("movieDate")
        val mPoster = intent.getIntExtra("moviePoster", 0)

        tvScene.findViewById<TextView>(R.id.m_title).text = mTitle
        tvScene.findViewById<TextView>(R.id.m_date).text = mDate
        tvScene.findViewById<ImageView>(R.id.m_img).setImageResource(mPoster)
    }
}