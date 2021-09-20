package com.example.filmatory.scenes

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.filmatory.R
import com.google.android.material.navigation.NavigationView


class LoginScene : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        drawerLayout= findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openNavDrawer,
            R.string.closeNavDrawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    setContentView(R.layout.start_screen)
                }
                R.id.nav_movies -> {
                    Toast.makeText(applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_series -> {
                    Toast.makeText(applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_upcoming_movies -> {
                    Toast.makeText(applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_upcoming_tvshows -> {
                    Toast.makeText(applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_lists -> {
                    Toast.makeText(applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_profile -> {
                    Toast.makeText(applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_favorites -> {
                    Toast.makeText(applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_watchlist -> {
                    Toast.makeText(applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_my_lists -> {
                    Toast.makeText(applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_logout -> {
                    setContentView(R.layout.register_screen)
                }
                R.id.nav_user_login -> {
                    setContentView(R.layout.register_screen)
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

