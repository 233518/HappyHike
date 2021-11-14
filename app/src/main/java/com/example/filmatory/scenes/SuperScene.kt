package com.example.filmatory.scenes

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.SearchScene
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.android.material.navigation.NavigationView

/**
 * SuperScene is the main scene for all the scenes. Every scene will extend this class
 *
 */
open class SuperScene : AppCompatActivity() {
    var auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.filmatoryTheme)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem : MenuItem = menu.findItem(R.id.action_search)
        val searchView : SearchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(applicationContext, SearchScene::class.java)
                intent.putExtra("title", query)
                applicationContext.startActivity(intent)
                println(query)
                return false
            }
        })

        return true
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        val navigationView : NavigationView = findViewById(R.id.nav_view)
        if(currentUser != null){
            Log.d(TAG, "User is signed in")
            navigationView.menu.clear()
            navigationView.inflateMenu(R.menu.nav_menu_logged_in)
        }else {
            navigationView.menu.clear()
            navigationView.inflateMenu(R.menu.nav_menu_logged_out)
        }
    }
}