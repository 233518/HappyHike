package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.Tv
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.TvScene
import com.example.filmatory.utils.PersonItem
import com.example.filmatory.utils.PersonRecyclerViewAdapter

/**
 * TvController manipulates the TvScene gui
 *
 * @param tvScene The TvScene to use
 */
class TvController(private val tvScene: TvScene) : MainController(tvScene) {
    var intent: Intent = tvScene.intent
    private val tvId = intent.getIntExtra("tvId", 0)
    private val personsArrayList: MutableList<PersonItem> = ArrayList()
    private val personsRecyclerView: RecyclerView = tvScene.findViewById(R.id.m_person_slider)
    private val personsAdapter = PersonRecyclerViewAdapter(personsArrayList, tvScene)

    init {
        apiSystem.requestTV(tvId.toString(), ::getTv)
    }

    /**
     * Update the gui with data from API
     *
     * @param tv The response from API
     */
    private fun getTv(tv : Tv){
        tvScene.runOnUiThread(Runnable {
            tvScene.findViewById<TextView>(R.id.m_title).text = tv.serieinfo.name
            tvScene.findViewById<TextView>(R.id.m_date).text = tv.serieinfo.first_air_date
            Glide.with(tvScene)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + tv.serieinfo.poster_path)
                .placeholder(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(tvScene.findViewById(R.id.m_img))
            tvScene.findViewById<TextView>(R.id.m_overview).text = tv.serieinfo.overview
            for (item in tv.personer.cast){
                personsArrayList.add(PersonItem(item.name,item.character,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.profile_path,item.id))
            }
            personsRecyclerView.layoutManager = LinearLayoutManager(tvScene, LinearLayoutManager.HORIZONTAL, false)
            personsRecyclerView.adapter = personsAdapter
        })
    }
}