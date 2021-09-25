package com.example.filmatory.scenes

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.StartController
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.media.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter

class StartScene : AppCompatActivity() {
    private lateinit var startController: StartController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        startController = StartController(this)
    }
}