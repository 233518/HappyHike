package com.example.filmatory.scenes


import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.TvsController

class TvsScene : SuperScene() {
    private lateinit var tvsController: TvsController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        tvsController = TvsController(this)
    }

}