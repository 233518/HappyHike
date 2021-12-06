package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmatory.api.data.movie.MovieFrontpage
import com.example.filmatory.api.data.tv.TvFrontpage
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.StartGui
import com.example.filmatory.scenes.activities.StartScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel

/**
 * StartController manipulates the StartScene gui
 *
 * @param startScene The StartScene to use
 */
class StartController(private val startScene: StartScene) : MainController(startScene) {
    private val startGui = StartGui(startScene, this)

    init {
        apiSystem.requestMovieFrontpageDiscover(RequestBaseOptions(null, null, ::discoverMoviesData, ::onFailure), languageCode)
        apiSystem.requestTvFrontpageDiscover(RequestBaseOptions(null, null, ::discoverTvData, ::onFailure), languageCode)
        apiSystem.requestMovieFrontpageRecommend(RequestBaseOptions(null, uid, ::recMovieData, ::onFailure), languageCode)
        apiSystem.requestTvFrontpageRecommend(RequestBaseOptions(null, uid, ::recTvData, ::onFailure), languageCode)
    }

    /**
     * Update the gui with data from API
     *
     * @param movieFrontpage The response from API
     */
    private fun discoverMoviesData(movieFrontpage: MovieFrontpage){
        val discoverMoviesArraylist: ArrayList<MediaModel> = ArrayList()
        val discoverMoviesAdapter = DataAdapter(startScene, this, startScene, discoverMoviesArraylist)

        movieFrontpage.forEach { item ->
            discoverMoviesArraylist.add(
                MediaModel(
                    DataAdapter.TYPE_MOVIE_SLIDER,
                    item.original_title,
                    item.release_date,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path,
                    item.id
                )
            )
        }
        startScene.runOnUiThread {
            startGui.discoverMoviesRecyclerView.layoutManager =
                LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            startGui.discoverMoviesRecyclerView.adapter = discoverMoviesAdapter
        }
    }

    /**
     * Update the gui with data from API
     *
     * @param tvFrontpage The respons from API
     */
    private fun discoverTvData(tvFrontpage: TvFrontpage){
        val discoverTvsArrayList: ArrayList<MediaModel> = ArrayList()
        val discoverTvsAdapter = DataAdapter(startScene,this, startScene, discoverTvsArrayList)

        tvFrontpage.forEach { item ->
            discoverTvsArrayList.add(
                MediaModel(
                    DataAdapter.TYPE_TV_SLIDER,
                    item.name,
                    item.first_air_date,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path,
                    item.id
                )
            )
        }
        startScene.runOnUiThread {
            startGui.discoverTvsRecyclerView.layoutManager =
                LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            startGui.discoverTvsRecyclerView.adapter = discoverTvsAdapter
        }
    }

    /**
     * Update the gui with data from API
     *
     * @param movieFrontpage The respons from API
     */
    private fun recMovieData(movieFrontpage: MovieFrontpage){
        val recMoviesArrayList: ArrayList<MediaModel> = ArrayList()
        val redMoviesAdapter = DataAdapter(startScene,this, startScene, recMoviesArrayList)

        movieFrontpage.forEach { item ->
            recMoviesArrayList.add(
                MediaModel(
                    DataAdapter.TYPE_MOVIE_SLIDER,
                    item.original_title,
                    item.release_date,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path,
                    item.id
                )
            )
        }
        startScene.runOnUiThread {
            startGui.recMoviesRecyclerView.layoutManager =
                LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            startGui.recMoviesRecyclerView.adapter = redMoviesAdapter
        }
    }

    /**
     * Update the gui with data from API
     *
     * @param tvFrontpage The respons from API
     */
    private fun recTvData(tvFrontpage: TvFrontpage){
        val recTvsArrayList: ArrayList<MediaModel> = ArrayList()
        val recTvsAdapter = DataAdapter(startScene,this, startScene, recTvsArrayList)

        tvFrontpage.forEach { item ->
            recTvsArrayList.add(
                MediaModel(
                    DataAdapter.TYPE_TV_SLIDER,
                    item.name,
                    item.first_air_date,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path,
                    item.id
                )
            )
        }
        startScene.runOnUiThread {
            startGui.recTvsRecyclerView.layoutManager =
                LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            startGui.recTvsRecyclerView.adapter = recTvsAdapter
        }
    }
}