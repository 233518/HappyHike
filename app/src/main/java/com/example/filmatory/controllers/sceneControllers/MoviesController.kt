package com.example.filmatory.controllers.sceneControllers

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.MoviesScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel
import kotlin.collections.ArrayList




/**
 * MoviesController manipulates the MoviesScene gui
 *
 * @param moviesScene The MoviesScene to use
 */
class MoviesController(private val moviesScene: MoviesScene) : MainController(moviesScene) {
    private val moviesPopularDesc : ArrayList<MediaModel> = ArrayList()
    private val moviesFilteredAZ : ArrayList<MediaModel> = ArrayList()
    private val moviesFilteredDateAsc : ArrayList<MediaModel> = ArrayList()
    private lateinit var moviesPopularAsc: ArrayList<MediaModel>
    private lateinit var moviesFilteredZA: ArrayList<MediaModel>
    private lateinit var moviesFilteredDateDesc: ArrayList<MediaModel>
    private val moviesRecyclerView: RecyclerView = moviesScene.findViewById(R.id.recyclerView)

    private val spinner : Spinner = moviesScene.findViewById(R.id.filter_spinner)

    init {
        apiSystem.requestMovies(RequestBaseOptions(null, null, ::moviesData, ::onFailure))
        apiSystem.requestMoviesFilterTitleAZ(RequestBaseOptions(null, null, ::moviesDataFilterTitle, ::onFailure))
        apiSystem.requestMoviesFilterDateDesc(RequestBaseOptions(null, null, ::moviesDataFilterDate, ::onFailure))

        ArrayAdapter.createFromResource(moviesScene, R.array.filter_array, android.R.layout.simple_spinner_dropdown_item).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.visibility = View.VISIBLE
            spinner.adapter = adapter
        }

        //Pass the scene to the listener that implements OnItemSelectedListener
        spinner.onItemSelectedListener = moviesScene
    }

    fun onFailure(baseError: BaseError) {

    }

    /**
     * Sets data from API to Arraylists and displays popular movies descending on activity start
     *
     * @param movies The response from API
     */
    private fun moviesData(movies: Movies){
        movies.forEach { item ->
            moviesPopularDesc.add(MediaModel(DataAdapter.TYPE_MOVIE, item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        moviesPopularAsc = ArrayList(moviesPopularDesc)
        moviesPopularAsc.reverse()

        moviesScene.runOnUiThread {
            val moviesAdapter = DataAdapter(moviesScene, moviesScene, moviesPopularDesc)
            moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesRecyclerView.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Sets data from API to Arraylists
     *
     * @param movies The response from API
     */
    private fun moviesDataFilterTitle(movies: Movies){
        movies.forEach{
            item -> moviesFilteredAZ.add(MediaModel(DataAdapter.TYPE_MOVIE, item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        moviesFilteredZA = ArrayList(moviesFilteredAZ)
        moviesFilteredZA.reverse()
    }

    /**
     * Sets data from API to Arraylists
     *
     * @param movies The response from API
     */
    private fun moviesDataFilterDate(movies: Movies){
        movies.forEach{
            item -> moviesFilteredDateAsc.add(MediaModel(DataAdapter.TYPE_MOVIE, item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        moviesFilteredDateDesc = ArrayList(moviesFilteredDateAsc)
        moviesFilteredDateDesc.reverse()
    }

    /**
     * Function to display movies descending by popularity
     *
     */
    private fun moviesPopularDesc(){
        val moviesAdapter = DataAdapter(moviesScene, moviesScene, moviesPopularDesc)
        moviesScene.runOnUiThread {
            moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesRecyclerView.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display movies ascending by popularity
     *
     */
    private fun moviesPopularAsc(){
        val moviesAdapter = DataAdapter(moviesScene, moviesScene, moviesPopularAsc)
        moviesScene.runOnUiThread {
            moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesRecyclerView.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display movies alphabetically
     *
     */
    private fun moviesTitleAZ(){
        val moviesAdapter = DataAdapter(moviesScene, moviesScene, moviesFilteredAZ)
        moviesScene.runOnUiThread {
            moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesRecyclerView.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display movies reversed alphabetically
     *
     */
    private fun moviesTitleZA(){
        val moviesAdapter = DataAdapter(moviesScene, moviesScene, moviesFilteredZA)
        moviesScene.runOnUiThread {
            moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesRecyclerView.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display movies ascending by date
     *
     */
    private fun moviesDateAsc(){
        val moviesAdapter = DataAdapter(moviesScene, moviesScene, moviesFilteredDateAsc)
        moviesScene.runOnUiThread {
            moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesRecyclerView.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Function to display movies descending by date
     *
     */
    private fun moviesDateDesc(){
        val moviesAdapter = DataAdapter(moviesScene, moviesScene, moviesFilteredDateDesc)
        moviesScene.runOnUiThread {
            moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesRecyclerView.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Listens to spinner changes, displays data accordingly
     *
     * @param itemAtPosition Chosen item in spinner
     */
    fun onNewSelected(itemAtPosition: Any) {
        when(spinner.selectedItemPosition){
            0 -> moviesPopularDesc()
            1 -> moviesPopularAsc()
            2 -> moviesDateAsc()
            3 -> moviesDateDesc()
            4 -> moviesTitleAZ()
            5 -> moviesTitleZA()
            else -> {
                moviesPopularDesc()
            }
        }
    }
}