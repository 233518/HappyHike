package com.example.filmatory.scenes

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R

open class SuperScene : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}