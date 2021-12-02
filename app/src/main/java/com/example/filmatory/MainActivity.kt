package com.example.filmatory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.filmatory.scenes.activities.StartScene
import com.yariksoffice.lingver.Lingver

/**
 * Main activity - This is where everything starts
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Lingver.init(application, "en")
        setContentView(R.layout.activity_main)
        val intent = Intent(this, StartScene::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        finish()
        startActivity(intent)
    }
}