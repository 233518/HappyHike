package com.example.filmatory.scenes

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.systems.NavSystem

class StartScene : AppCompatActivity() {
    private lateinit var navSystem: NavSystem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        navSystem = NavSystem(this)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(navSystem.toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}