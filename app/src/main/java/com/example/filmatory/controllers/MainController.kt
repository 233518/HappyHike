package com.example.filmatory.controllers

import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.ApiSystem

/**
 * MainController manipulates the scene gui
 * Every controller will extend this class
 *
 * @constructor
 *
 * @param scene The scene the controller will be connected to
 */
open class MainController(scene : AppCompatActivity) {
    protected val navSystem = NavSystem(scene)
    protected val apiSystem = ApiSystem()
}