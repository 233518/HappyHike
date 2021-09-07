package com.example.happyhike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import android.content.Intent
import com.example.happyhike.scenes.KartScene


class MainActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, KartScene::class.java)
        startActivity(intent)
    }
}

