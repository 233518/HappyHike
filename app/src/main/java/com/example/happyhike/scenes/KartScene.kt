package com.example.happyhike.scenes

import OnMapReadyCallbackcom.example.happyhike.controllers.KartController
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.happyhike.R

class KartScene : AppCompatActivity() {
    //Variables
    lateinit var kartController: KartController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        kartController = KartController(this)
    }

}