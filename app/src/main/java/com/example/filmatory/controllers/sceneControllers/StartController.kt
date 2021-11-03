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
import com.example.filmatory.utils.items.MediaItem
import com.example.filmatory.utils.adapters.SliderAdapter
import com.example.filmatory.utils.adapters.TvSliderAdapter

/**
 * StartController manipulates the StartScene gui
 *
 * @param startScene The StartScene to use
 */
class StartController(private val startScene: StartScene) : MainController(startScene) {

    init {
        apiSystem.requestMovieFrontpage(RequestBaseOptions(null, null, ::discoverMoviesData, ::onFailure))
        apiSystem.requestTvFrontpage(RequestBaseOptions(null, null, ::discoverTvData, ::onFailure))
        apiSystem.requestMovieFrontpage(RequestBaseOptions(null, null, ::recMovieData, ::onFailure))
        apiSystem.requestTvFrontpage(RequestBaseOptions(null, null, ::recTvData, ::onFailure))
        //apiSystem.requestMovie(RequestBaseOptions("dwqdwqdwq", null, ::test, ::onFailure))
        //snackbarSystem.showSnackbarFailure("Something unexpected happen!", ::test, "Retry")
    }

    fun test(any: Any) {

    }

    fun onFailure(baseError: BaseError) {

    }

    /**
     * Update the gui with data from API
     *
     * @param movieFrontpage The response from API
     */
    private fun discoverMoviesData(movieFrontpage: MovieFrontpage){
        startScene.runOnUiThread(Runnable {
            val discoverMoviesArraylist: MutableList<MediaItem> = ArrayList()
            val discoverMoviesAdapter = SliderAdapter(discoverMoviesArraylist,startScene)
            val discoverMoviesRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view)
            movieFrontpage.forEach{
                    item -> discoverMoviesArraylist.add(MediaItem(item.original_title, item.release_date, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path, item.id))
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
            val discoverTvsArrayList: MutableList<MediaItem> = ArrayList()
            val discoverTvsAdapter = TvSliderAdapter(discoverTvsArrayList,startScene)
            val discoverTvsRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view2)
            tvFrontpage.forEach{
                    item -> discoverTvsArrayList.add(MediaItem(item.name, item.first_air_date, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path, item.id))
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
            val recMoviesArrayList: MutableList<MediaItem> = ArrayList()
            val redMoviesAdapter = SliderAdapter(recMoviesArrayList, startScene)
            val recMoviesRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view3)
            movieFrontpage.forEach{
                    item -> recMoviesArrayList.add(MediaItem(item.original_title, item.release_date, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path, item.id))
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
            val recTvsArrayList: MutableList<MediaItem> = ArrayList()
            val recTvsAdapter = TvSliderAdapter(recTvsArrayList, startScene)
            val recTvsRecyclerView: RecyclerView = startScene.findViewById(R.id.slider_recycler_view4)
            tvFrontpage.forEach{
                    item -> recTvsArrayList.add(MediaItem(item.name, item.first_air_date, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.poster_path, item.id))
            }
            recTvsRecyclerView.layoutManager = LinearLayoutManager(startScene, LinearLayoutManager.HORIZONTAL, false)
            recTvsRecyclerView.adapter = recTvsAdapter
        })
    }
}