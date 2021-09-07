package com.example.happyhike.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.happyhike.R
import com.example.happyhike.controllers.KartController
import com.google.android.gms.maps.SupportMapFragment

class KartScene : AppCompatActivity() {
    //Variables
    lateinit var kartController: KartController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        kartController = KartController(this)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
    }

}