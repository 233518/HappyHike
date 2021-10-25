package com.example.filmatory.controllers.sceneControllers

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movie
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.utils.MediaItem
import com.example.filmatory.utils.PersonItem
import com.example.filmatory.utils.PersonRecyclerViewAdapter
import com.example.filmatory.utils.RecyclerViewAdapter

class MovieController(movieScene: MovieScene) : MainController(movieScene) {
    val movieScene = movieScene
    var intent = movieScene.intent
    val mId = intent.getIntExtra("movieId", 0)
    val personsArrayList: MutableList<PersonItem> = ArrayList()
    val personsRecyclerView: RecyclerView = movieScene.findViewById(R.id.m_person_slider)
    val personsAdapter = PersonRecyclerViewAdapter(personsArrayList, movieScene)


    init {
        apiSystem.requestMovie(mId.toString() ,::getMovie)
    }

    fun getMovie(movie: Movie){
        movieScene.runOnUiThread(Runnable {
            movieScene.findViewById<TextView>(R.id.m_title).text = movie.filminfo.title
            movieScene.findViewById<TextView>(R.id.m_date).text = movie.filminfo.release_date
            Glide.with(movieScene)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movie.filminfo.poster_path)
                .placeholder(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(movieScene.findViewById(R.id.m_img))
            movieScene.findViewById<TextView>(R.id.m_overview).text = movie.filminfo.overview

            for (item in movie.cast.cast){
                personsArrayList.add(PersonItem(item.name,item.character,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.profile_path,item.id))
            }
            personsRecyclerView.layoutManager = LinearLayoutManager(movieScene, LinearLayoutManager.HORIZONTAL, false)
            personsRecyclerView.adapter = personsAdapter

        })
    }
}