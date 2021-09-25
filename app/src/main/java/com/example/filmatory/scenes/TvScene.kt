package com.example.filmatory.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.filmatory.R
import com.example.filmatory.controllers.TvController
import com.example.filmatory.systems.NavSystem

class TvScene : AppCompatActivity() {
    private lateinit var tvController: TvController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_scene)
        tvController = TvController(this)
    }
}