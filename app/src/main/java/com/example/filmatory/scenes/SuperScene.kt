package com.example.filmatory.scenes

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import com.google.android.material.navigation.NavigationView

/**
 * SuperScene is the main scene for all the scenes. Every scene will extend this class
 *
 */
open class SuperScene : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.filmatoryTheme)
        auth = Firebase.auth
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
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