package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movie
import com.example.filmatory.api.data.movie.WatchProviders
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.utils.PersonItem
import com.example.filmatory.utils.PersonRecyclerViewAdapter

/**
 * MovieController manipulates the MovieScene gui
 *
 * @param movieScene The MovieScene to use
 */
class MovieController(val movieScene: MovieScene) : MainController(movieScene) {
    var intent: Intent = movieScene.intent
    private val mId = intent.getIntExtra("movieId", 0)
    private val personsArrayList: MutableList<PersonItem> = ArrayList()
    private val personsRecyclerView: RecyclerView = movieScene.findViewById(R.id.m_person_slider)
    private val personsAdapter = PersonRecyclerViewAdapter(personsArrayList, movieScene)

    init {
        apiSystem.requestMovie(mId.toString() ,::getMovie)
        apiSystem.requestWatchProviders(mId.toString(), ::getWatchprovider)
        personsRecyclerView.layoutManager = LinearLayoutManager(movieScene, LinearLayoutManager.HORIZONTAL, false)
        personsRecyclerView.adapter = personsAdapter
    }

    private fun getWatchprovider(watchProviders: WatchProviders){
        movieScene.runOnUiThread(Runnable {
            if(watchProviders.results.NO != null){
                if (!watchProviders.results.NO.flatrate.isNullOrEmpty()) {
                    movieScene.findViewById<TextView>(R.id.m_streaming_on).visibility = View.VISIBLE
                    for (item in watchProviders.results.NO.flatrate) {
                        if (item.provider_name == "Netflix") {
                            movieScene.findViewById<View>(R.id.m_s_netflix_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_netflix_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            movieScene.findViewById<View>(R.id.m_s_hbo_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_hbo_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            movieScene.findViewById<View>(R.id.m_s_viaplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_viaplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            movieScene.findViewById<View>(R.id.m_s_disneyplus_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_disneyplus_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            movieScene.findViewById<View>(R.id.m_s_strim_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_strim_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            movieScene.findViewById<View>(R.id.m_s_googleplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_googleplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            movieScene.findViewById<View>(R.id.m_s_itunes_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_s_itunes_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
                if (!watchProviders.results.NO.rent.isNullOrEmpty()) {
                    movieScene.findViewById<TextView>(R.id.m_rent_on).visibility = View.VISIBLE
                    for (item in watchProviders.results.NO.rent) {
                        if (item.provider_name == "Netflix") {
                            movieScene.findViewById<View>(R.id.m_r_netflix_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_netflix_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            movieScene.findViewById<View>(R.id.m_r_hbo_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_hbo_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            movieScene.findViewById<View>(R.id.m_r_viaplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_viaplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            movieScene.findViewById<View>(R.id.m_r_disneyplus_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_disneyplus_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            movieScene.findViewById<View>(R.id.m_r_strim_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_strim_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            movieScene.findViewById<View>(R.id.m_r_googleplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_googleplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            movieScene.findViewById<View>(R.id.m_r_itunes_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_r_itunes_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                    }
                }
                if (!watchProviders.results.NO.buy.isNullOrEmpty()) {
                    movieScene.findViewById<TextView>(R.id.m_buy_on).visibility = View.VISIBLE
                    for (item in watchProviders.results.NO.buy) {
                        if (item.provider_name == "Netflix") {
                            movieScene.findViewById<View>(R.id.m_b_netflix_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_netflix_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "HBO Max") {
                            movieScene.findViewById<View>(R.id.m_b_hbo_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_hbo_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Viaplay") {
                            movieScene.findViewById<View>(R.id.m_b_viaplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_viaplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Disney Plus") {
                            movieScene.findViewById<View>(R.id.m_b_disneyplus_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_disneyplus_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Strim") {
                            movieScene.findViewById<View>(R.id.m_b_strim_logo).visibility = View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_strim_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Google Play Movies") {
                            movieScene.findViewById<View>(R.id.m_b_googleplay_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_googleplay_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
                                    )
                                )
                            }
                        }
                        if (item.provider_name == "Apple iTunes") {
                            movieScene.findViewById<View>(R.id.m_b_itunes_logo).visibility =
                                View.VISIBLE
                            movieScene.findViewById<View>(R.id.m_b_itunes_logo).setOnClickListener {
                                movieScene.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(watchProviders.results.NO.link)
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
     * @param movie The response from API
     */
    private fun getMovie(movie: Movie){

        movieScene.runOnUiThread(Runnable {
            movieScene.findViewById<TextView>(R.id.m_title).text = movie.filminfo.title
            movieScene.findViewById<TextView>(R.id.m_date).text = movie.filminfo.release_date
            Glide.with(movieScene)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movie.filminfo.poster_path)
                .placeholder(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(movieScene.findViewById(R.id.m_img))
            movieScene.findViewById<TextView>(R.id.m_overview).text = movie.filminfo.overview
            movie.cast.cast.take(10).forEach {
                item -> personsArrayList.add(PersonItem(item.name,item.character,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.profile_path,item.id))
            }
            personsAdapter.notifyDataSetChanged()
        })
    }
}