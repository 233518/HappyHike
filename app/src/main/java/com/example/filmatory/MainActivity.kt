package com.example.filmatory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import android.content.Intent
import com.example.filmatory.scenes.LoginScene
import com.example.filmatory.scenes.MoviesScene
import com.example.filmatory.systems.media.Movie


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, MoviesScene::class.java)
        startActivity(intent)
    }
}