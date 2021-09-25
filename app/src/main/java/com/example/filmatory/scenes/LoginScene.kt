package com.example.filmatory.scenes

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.filmatory.R
import com.example.filmatory.api.ApiRespons
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.api.ApiSystem
import com.google.android.material.navigation.NavigationView

class LoginScene : AppCompatActivity() {
    private var apiSystem = ApiSystem()
    private lateinit var navSystem: NavSystem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiSystem.requestApprovedReviewById("60b3a9194001540015069d2c", ::printTest)
        setContentView(R.layout.login_screen)
        navSystem = NavSystem(this)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(navSystem.toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun printTest(apiRespons: ApiRespons) {
        println(apiRespons)
    }
}

