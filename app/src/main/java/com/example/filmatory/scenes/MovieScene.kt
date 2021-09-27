package com.example.filmatory.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.MovieController
import com.example.filmatory.utils.BlurImage

class MovieScene : SuperScene() {
    private lateinit var movieController : MovieController
    private var blurImage: BlurImage = BlurImage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_scene)
        movieController = MovieController(this)
        blurImage.blurImage(this, R.drawable.image2, findViewById(R.id.movie_imgView))
    }
}