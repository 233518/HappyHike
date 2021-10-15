package com.example.filmatory.controllers.sceneControllers

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movie
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.SliderAdapter

class MovieController(movieScene: MovieScene) : MainController(movieScene) {
    val movieScene = movieScene

    init {
        //apiSystem.requestMovie("580489" ,::showMovie)
    }

    fun showMovie(movie: Movie){
        movieScene.runOnUiThread(Runnable {
            var intent = movieScene.intent
            val mTitle = intent.getStringExtra("movieTitle")
            val mDate = intent.getStringExtra("movieDate")
            val mPoster = intent.getIntExtra("moviePoster", 0)

            movieScene.findViewById<TextView>(R.id.m_title).text = mTitle
            movieScene.findViewById<TextView>(R.id.m_date).text = mDate
            movieScene.findViewById<ImageView>(R.id.m_img).setImageResource(mPoster)
        })
    }
}