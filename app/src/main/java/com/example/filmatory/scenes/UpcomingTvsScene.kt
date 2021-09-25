package com.example.filmatory.scenes


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.UpcomingTvsController

class UpcomingTvsScene : AppCompatActivity() {
    private lateinit var upcomingTvsController: UpcomingTvsController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        upcomingTvsController = UpcomingTvsController(this)
    }
}