package com.example.filmatory.systems

import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.transition.Scene
import com.example.filmatory.R
import com.example.filmatory.scenes.*
import com.google.android.material.navigation.NavigationView

class NavSystem(appCompatActivity: AppCompatActivity){
    lateinit var toggle: ActionBarDrawerToggle
    private var drawerLayout: DrawerLayout = appCompatActivity.findViewById(R.id.drawer_layout)
    private var navigationView: NavigationView = appCompatActivity.findViewById(R.id.nav_view)
    private var toolbar: Toolbar = appCompatActivity.findViewById(R.id.main_toolbar)

    init{
        appCompatActivity.setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            appCompatActivity,
        drawerLayout,
        toolbar,
        R.string.openNavDrawer,
        R.string.closeNavDrawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

            appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    val intent = Intent(appCompatActivity, StartScene::class.java)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_movies -> {
                    val intent = Intent(appCompatActivity, MoviesScene::class.java)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_series -> {
                    val intent = Intent(appCompatActivity, TvsScene::class.java)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_upcoming_movies -> {
                    val intent = Intent(appCompatActivity, UpcomingMoviesScene::class.java)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_upcoming_tvshows -> {
                    val intent = Intent(appCompatActivity, UpcomingTvsScene::class.java)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_lists -> {
                    Toast.makeText(appCompatActivity.applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_profile -> {
                    Toast.makeText(appCompatActivity.applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_favorites -> {
                    Toast.makeText(appCompatActivity.applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_watchlist -> {
                    Toast.makeText(appCompatActivity.applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_my_lists -> {
                    Toast.makeText(appCompatActivity.applicationContext,
                        "Clicked item", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_user_logout -> {
                    val intent = Intent(appCompatActivity, RegisterScene::class.java)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_login -> {
                    val intent = Intent(appCompatActivity, LoginScene::class.java)
                    appCompatActivity.startActivity(intent)
                }
            }
            true
        }

    }

}