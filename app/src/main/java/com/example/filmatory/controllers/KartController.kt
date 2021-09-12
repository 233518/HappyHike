package com.example.filmatory.controllers

import com.example.filmatory.R
import com.example.filmatory.guis.KartGui
import com.example.filmatory.scenes.KartScene
import com.example.filmatory.systems.map.MapSystem
import com.google.android.gms.maps.SupportMapFragment

class KartController(kartScene : KartScene) {
    //Init later
    private lateinit var kartGui: KartGui
    lateinit var mapSystem: MapSystem

    //Init now
    var kartScene = kartScene;

    init {
        kartGui = KartGui()
        mapSystem = MapSystem(kartScene.supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
    }
}