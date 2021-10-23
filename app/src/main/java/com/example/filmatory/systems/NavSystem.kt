package com.example.filmatory.systems

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class NavSystem(appCompatActivity: AppCompatActivity)  {
    var toggle: ActionBarDrawerToggle
    private var drawerLayout: DrawerLayout = appCompatActivity.findViewById(R.id.drawer_layout)
    private var navigationView: NavigationView = appCompatActivity.findViewById(R.id.nav_view)
    private var toolbar: Toolbar = appCompatActivity.findViewById(R.id.main_toolbar)

    init{
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle = ActionBarDrawerToggle(
            appCompatActivity,
        drawerLayout,
        toolbar,
        R.string.openNavDrawer,
        R.string.closeNavDrawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



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
                    val intent = Intent(appCompatActivity, ListsScene::class.java)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_profile -> {
                    val intent = Intent(appCompatActivity, AccountScene::class.java)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_favorites -> {
                    val intent = Intent(appCompatActivity, AccountInfoScene::class.java)
                    intent.putExtra("position", 1)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_watchlist -> {
                    val intent = Intent(appCompatActivity, AccountInfoScene::class.java)
                    intent.putExtra("position", 2)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_my_lists -> {
                    val intent = Intent(appCompatActivity, AccountInfoScene::class.java)
                    intent.putExtra("position", 3)
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_logout -> {
                    Firebase.auth.signOut()
                    val intent = Intent(appCompatActivity, StartScene::class.java)
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
