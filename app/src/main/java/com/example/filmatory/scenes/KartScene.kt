package com.example.filmatory.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.controllers.KartController

class KartScene : AppCompatActivity() {
    //Variables
    lateinit var kartController: KartController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        kartController = KartController(this)
    }

    fun startVideo() {

    }

}