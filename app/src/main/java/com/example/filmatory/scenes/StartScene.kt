package com.example.filmatory.scenes

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.media.MediaItem
import com.example.filmatory.utils.RecyclerViewAdapter

class StartScene : AppCompatActivity() {
    private lateinit var navSystem: NavSystem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        navSystem = NavSystem(this)


        val discoverMoviesArraylist: MutableList<MediaItem> = ArrayList()
        val discoverMoviesAdapter = RecyclerViewAdapter(discoverMoviesArraylist,this)
        val discoverMoviesRecyclerView: RecyclerView = findViewById(R.id.slider_recycler_view)
        discoverMoviesArraylist.add(MediaItem("Movie 1", "Dato 1", R.drawable.movie1))
        discoverMoviesArraylist.add(MediaItem("Movie 2", "Dato 2", R.drawable.movie2))
        discoverMoviesArraylist.add(MediaItem("Movie 3", "Dato 3", R.drawable.movie3))
        discoverMoviesArraylist.add(MediaItem("Movie 4", "Dato 4", R.drawable.movie4))
        discoverMoviesArraylist.add(MediaItem("Movie 5", "Dato 5", R.drawable.movie5))
        discoverMoviesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        discoverMoviesRecyclerView.adapter = discoverMoviesAdapter

        val discoverTvsArrayList: MutableList<MediaItem> = ArrayList()
        val discoverTvsAdapter = RecyclerViewAdapter(discoverTvsArrayList,this)
        val discoverTvsRecyclerView: RecyclerView = findViewById(R.id.slider_recycler_view2)
        discoverTvsArrayList.add(MediaItem("Movie 6", "Dato 6", R.drawable.movie6))
        discoverTvsArrayList.add(MediaItem("Movie 7", "Dato 7", R.drawable.movie7))
        discoverTvsArrayList.add(MediaItem("Movie 8", "Dato 8", R.drawable.movie8))
        discoverTvsArrayList.add(MediaItem("Movie 9", "Dato 9", R.drawable.movie9))
        discoverTvsArrayList.add(MediaItem("Movie 10", "Dato 10", R.drawable.movie10))
        discoverTvsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        discoverTvsRecyclerView.adapter = discoverTvsAdapter

        val recMoviesArrayList: MutableList<MediaItem> = ArrayList()
        val redMoviesAdapter = RecyclerViewAdapter(recMoviesArrayList, this)
        val recMoviesRecyclerView: RecyclerView = findViewById(R.id.slider_recycler_view3)
        recMoviesArrayList.add(MediaItem("Movie 11", "Dato 11", R.drawable.movie11))
        recMoviesArrayList.add(MediaItem("Movie 12", "Dato 12", R.drawable.movie12))
        recMoviesArrayList.add(MediaItem("Movie 13", "Dato 13", R.drawable.movie13))
        recMoviesArrayList.add(MediaItem("Movie 14", "Dato 14", R.drawable.movie14))
        recMoviesArrayList.add(MediaItem("Movie 15", "Dato 15", R.drawable.movie15))
        recMoviesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recMoviesRecyclerView.adapter = redMoviesAdapter

        val recTvsArrayList: MutableList<MediaItem> = ArrayList()
        val recTvsAdapter = RecyclerViewAdapter(recTvsArrayList, this)
        val recTvsRecyclerView: RecyclerView = findViewById(R.id.slider_recycler_view4)
        recTvsArrayList.add(MediaItem("Movie 16", "Dato 16", R.drawable.movie16))
        recTvsArrayList.add(MediaItem("Movie 17", "Dato 17", R.drawable.movie17))
        recTvsArrayList.add(MediaItem("Movie 18", "Dato 18", R.drawable.movie18))
        recTvsArrayList.add(MediaItem("Movie 19", "Dato 19", R.drawable.movie19))
        recTvsArrayList.add(MediaItem("Movie 20", "Dato 20", R.drawable.movie20))
        recTvsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recTvsRecyclerView.adapter = recTvsAdapter


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(navSystem.toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}