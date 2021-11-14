package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.MovieFrontpage
import com.example.filmatory.api.data.tv.TvFrontpage
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
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

    init {
        apiSystem.requestMovieFrontpage(RequestBaseOptions(null, null, ::discoverMoviesData, ::onFailure), languageCode)
        apiSystem.requestTvFrontpage(RequestBaseOptions(null, null, ::discoverTvData, ::onFailure), languageCode)
        apiSystem.requestMovieFrontpage(RequestBaseOptions(null, null, ::recMovieData, ::onFailure), languageCode)
        apiSystem.requestTvFrontpage(RequestBaseOptions(null, null, ::recTvData, ::onFailure), languageCode)
        apiSystem.requestTest(RequestBaseOptions(null, null, ::test, ::onFailure))
        //snackbarSystem.showSnackbarFailure("Something unexpected happen!", ::test, "Retry")
    }

    fun test(any: Any) {

    }

    fun onFailure(baseError: BaseError) {
        println(baseError.message)
    }

    /**
     * Update the gui with data from API
     *
     * @param movieFrontpage The response from API
     */
    private fun discoverMoviesData(movieFrontpage: MovieFrontpage){
        startScene.runOnUiThread(Runnable {
            val discoverMoviesArraylist: ArrayList<MediaModel> = ArrayList()
            val discoverMoviesAdapter = DataAdapter(startScene,discoverMoviesArraylist)
            val discoverMoviesRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view)
            movieFrontpage.forEach{
                    item -> discoverMoviesArraylist.add(MediaModel(DataAdapter.TYPE_MOVIE_SLIDER ,item.original_title, item.release_date, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path, item.id))
            }
            discoverMoviesRecyclerView.layoutManager = LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            discoverMoviesRecyclerView.adapter = discoverMoviesAdapter
        })
    }

    /**
     * Update the gui with data from API
     *
     * @param tvFrontpage The respons from API
     */
    private fun discoverTvData(tvFrontpage: TvFrontpage){
        startScene.runOnUiThread(Runnable {
            val discoverTvsArrayList: ArrayList<MediaModel> = ArrayList()
            val discoverTvsAdapter = DataAdapter(startScene, discoverTvsArrayList)
            val discoverTvsRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view2)
            tvFrontpage.forEach{
                    item -> discoverTvsArrayList.add(MediaModel(DataAdapter.TYPE_TV_SLIDER ,item.name, item.first_air_date, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path, item.id))
            }
            discoverTvsRecyclerView.layoutManager = LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            discoverTvsRecyclerView.adapter = discoverTvsAdapter
        })
    }

    /**
     * Update the gui with data from API
     *
     * @param movieFrontpage The respons from API
     */
    private fun recMovieData(movieFrontpage: MovieFrontpage){
        startScene.runOnUiThread(Runnable {
            val recMoviesArrayList: ArrayList<MediaModel> = ArrayList()
            val redMoviesAdapter = DataAdapter(startScene, recMoviesArrayList)
            val recMoviesRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view3)
            movieFrontpage.forEach{
                    item -> recMoviesArrayList.add(MediaModel(DataAdapter.TYPE_MOVIE_SLIDER, item.original_title, item.release_date, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path, item.id))
            }
            recMoviesRecyclerView.layoutManager = LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            recMoviesRecyclerView.adapter = redMoviesAdapter
        })
    }

    /**
     * Update the gui with data from API
     *
     * @param tvFrontpage The respons from API
     */
    private fun recTvData(tvFrontpage: TvFrontpage){
        startScene.runOnUiThread(Runnable {
            val recTvsArrayList: ArrayList<MediaModel> = ArrayList()
            val recTvsAdapter = DataAdapter(startScene, recTvsArrayList)
            val recTvsRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view4)
            tvFrontpage.forEach{
                    item -> recTvsArrayList.add(MediaModel(DataAdapter.TYPE_TV_SLIDER, item.name, item.first_air_date, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path, item.id))
            }
            recTvsRecyclerView.layoutManager = LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            recTvsRecyclerView.adapter = recTvsAdapter
        })
    }
}