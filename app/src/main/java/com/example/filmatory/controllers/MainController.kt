package com.example.filmatory.controllers

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.R
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.api.ApiSystem

open class MainController(activity : AppCompatActivity) {
    val navSystem = NavSystem(activity)
        get() = field
    val apiSystem = ApiSystem()

}
