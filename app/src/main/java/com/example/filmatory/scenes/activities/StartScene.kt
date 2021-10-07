package com.example.filmatory.scenes.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.StartController

class StartScene : SuperScene() {
    private lateinit var startController: StartController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        startController = StartController(this)
    }

}