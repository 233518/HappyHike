package com.example.filmatory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.filmatory.scenes.activities.StartScene

/**
 * Main activity - This is where everything starts
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, StartScene::class.java)
        startActivity(intent)
    }
}