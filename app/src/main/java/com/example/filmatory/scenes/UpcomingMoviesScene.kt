package com.example.filmatory.scenes


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.systems.NavSystem
import com.example.filmatory.systems.media.Movie
import com.example.filmatory.utils.RecyclerViewAdapter

class UpcomingMoviesScene : AppCompatActivity() {
    private lateinit var navSystem: NavSystem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_container)
        navSystem = NavSystem(this)
        val arrayList: MutableList<Movie> = ArrayList()
        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        arrayList.add(Movie("Movie 1", "Dato 1", R.drawable.movie1))
        arrayList.add(Movie("Movie 2", "Dato 2", R.drawable.movie2))
        arrayList.add(Movie("Movie 3", "Dato 3", R.drawable.movie3))
        arrayList.add(Movie("Movie 4", "Dato 4", R.drawable.movie4))
        arrayList.add(Movie("Movie 5", "Dato 5", R.drawable.movie5))
        arrayList.add(Movie("Movie 6", "Dato 6", R.drawable.movie6))
        arrayList.add(Movie("Movie 7", "Dato 7", R.drawable.movie7))
        arrayList.add(Movie("Movie 8", "Dato 8", R.drawable.movie8))
        arrayList.add(Movie("Movie 9", "Dato 9", R.drawable.movie9))
        arrayList.add(Movie("Movie 10", "Dato 10", R.drawable.movie10))

        val myAdapter = RecyclerViewAdapter(arrayList, this)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = myAdapter
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(navSystem.toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}