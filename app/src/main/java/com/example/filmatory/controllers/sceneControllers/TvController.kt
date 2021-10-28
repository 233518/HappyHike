package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.tv.Tv
import com.example.filmatory.api.data.tv.TvWatchProviders
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
        apiSystem.requestTvWatchProviders(tvId.toString(), ::getWatchprovider)
    }
    private fun getWatchprovider(tvWatchProviders: TvWatchProviders){
        tvScene.runOnUiThread(Runnable {
            if(tvWatchProviders.results.NO != null){
                if (!tvWatchProviders.results.NO.flatrate.isNullOrEmpty()) {
                    for (item in tvWatchProviders.results.NO.flatrate) {
                        if (item.provider_name == "Netflix") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_netflix_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_netflix_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_hbo_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_hbo_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_viaplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_viaplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_disneyplus_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_disneyplus_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_strim_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_strim_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_googleplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_googleplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            tvScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_itunes_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_s_itunes_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
                if (!tvWatchProviders.results.NO.rent.isNullOrEmpty()) {
                    for (item in tvWatchProviders.results.NO.rent) {
                        if (item.provider_name == "Netflix") {
                            tvScene.findViewById<View>(R.id.m_r_netflix_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_netflix_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_hbo_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_hbo_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_viaplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_viaplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_disneyplus_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_disneyplus_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_strim_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_strim_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_googleplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_googleplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            tvScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_itunes_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_r_itunes_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
                if (!tvWatchProviders.results.NO.buy.isNullOrEmpty()) {
                    for (item in tvWatchProviders.results.NO.buy) {
                        if (item.provider_name == "Netflix") {
                            tvScene.findViewById<View>(R.id.m_b_netflix_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_netflix_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_hbo_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_hbo_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_viaplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_viaplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_disneyplus_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_disneyplus_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_strim_logo).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_strim_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_googleplay_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_googleplay_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            tvScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_itunes_logo).visibility =
                                View.VISIBLE
                            tvScene.findViewById<View>(R.id.m_b_itunes_logo).setOnClickListener {
                                tvScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(tvWatchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        })
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