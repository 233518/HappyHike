package com.example.filmatory.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.filmatory.R

class MovieScene : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_scene)

        var intent = intent
        val mTitle = intent.getStringExtra("movieTitle")
        val mDate = intent.getStringExtra("movieDate")
        val mPoster = intent.getIntExtra("moviePoster", 0)

        findViewById<TextView>(R.id.m_title).text = mTitle
        findViewById<TextView>(R.id.m_date).text = mDate
        findViewById<ImageView>(R.id.m_img).setImageResource(mPoster)
    }
}