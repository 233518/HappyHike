package com.example.filmatory.scenes.activities


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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