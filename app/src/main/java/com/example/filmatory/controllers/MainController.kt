package com.example.filmatory.controllers

import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.ApiSystem

open class MainController(activity : AppCompatActivity) {
    val navSystem = NavSystem(activity)
        get() = field
    val apiSystem = ApiSystem()

}
