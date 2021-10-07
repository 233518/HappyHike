package com.example.filmatory.scenes.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.UpcomingTvsController
import com.example.filmatory.utils.BlurImage

class UpcomingTvsScene : SuperScene() {
    private lateinit var upcomingTvsController: UpcomingTvsController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        upcomingTvsController = UpcomingTvsController(this)
    }
}