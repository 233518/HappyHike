package com.example.filmatory.controllers

import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.api.data.movie.MovieFrontpage
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.ApiSystem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

open class MainController(activity : AppCompatActivity) {
    val db = Firebase.firestore
    val navSystem = NavSystem(activity)
        get() = field

    val apiSystem = ApiSystem()
}
