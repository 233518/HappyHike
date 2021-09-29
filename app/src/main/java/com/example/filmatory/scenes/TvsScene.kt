package com.example.filmatory.scenes


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.TvsController
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.media.MediaItem
import com.example.filmatory.utils.BlurImage
import com.example.filmatory.utils.RecyclerViewAdapter

class TvsScene : SuperScene() {
    private lateinit var tvsController: TvsController
    private var blurImage: BlurImage = BlurImage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        tvsController = TvsController(this)
        blurImage.blurImage(this, R.drawable.image9, findViewById(R.id.mediaImgView))
    }

}