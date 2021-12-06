package com.example.filmatory.controllers.sceneControllers

import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.guis.MoviesGui
import com.example.filmatory.scenes.activities.MoviesScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.collections.ArrayList

/**
 * MoviesController manipulates the MoviesScene gui
 *
 * @param moviesScene The MoviesScene to use
 */
class MoviesController(private val moviesScene: MoviesScene) : MainController(moviesScene) {
    private val moviesGui = MoviesGui(moviesScene, this)

    private val moviesPopularDesc : ArrayList<MediaModel> = ArrayList()
    private val moviesFilteredAZ : ArrayList<MediaModel> = ArrayList()
    private val moviesFilteredDateAsc : ArrayList<MediaModel> = ArrayList()

    private var moviesPopularAsc: ArrayList<MediaModel> = ArrayList()
    private var moviesFilteredZA: ArrayList<MediaModel> = ArrayList()
    private var moviesFilteredDateDesc: ArrayList<MediaModel> = ArrayList()

    init {
        apiSystem.requestMovies(RequestBaseOptions(null, null, ::moviesData, ::onFailure))
        apiSystem.requestMoviesFilterTitleAZ(RequestBaseOptions(null, null, ::moviesDataFilterTitle, ::onFailure))
        apiSystem.requestMoviesFilterDateDesc(RequestBaseOptions(null, null, ::moviesDataFilterDate, ::onFailure))
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
            moviesGui.moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesGui.moviesRecyclerView.adapter = moviesAdapter
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
            moviesGui.moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesGui.moviesRecyclerView.adapter = moviesAdapter
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
            moviesGui.moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesGui.moviesRecyclerView.adapter = moviesAdapter
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
            moviesGui.moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesGui.moviesRecyclerView.adapter = moviesAdapter
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
            moviesGui.moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesGui.moviesRecyclerView.adapter = moviesAdapter
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
            moviesGui.moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesGui.moviesRecyclerView.adapter = moviesAdapter
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
            moviesGui.moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesGui.moviesRecyclerView.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
    }

    fun showFilterList(){
        moviesScene.runOnUiThread {
            var chosenItem: Int = -1
            MaterialAlertDialogBuilder(moviesScene)
                .setTitle(moviesScene.resources.getString(R.string.filter))
                .setNeutralButton(moviesScene.resources.getString(R.string.cancel_btn)) { dialog, which -> }
                .setPositiveButton(moviesScene.resources.getString(R.string.confirm_btn)) { dialog, which ->
                    when(chosenItem){
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
                .setSingleChoiceItems(R.array.filter_array, chosenItem) { dialog, which ->
                    chosenItem = which
                }
            .show()
        }
    }

    fun showGenreFilterList(){
        moviesScene.runOnUiThread {
            var chosenItem: Int = -1
            MaterialAlertDialogBuilder(moviesScene)
                .setTitle(moviesScene.resources.getString(R.string.filter_genre))
                .setNeutralButton(moviesScene.resources.getString(R.string.close_btn)) { dialog, which -> }
                .setPositiveButton(moviesScene.resources.getString(R.string.confirm_btn)) { dialog, which ->
                    when(chosenItem){

                    }
                }
                .setSingleChoiceItems(R.array.filter_genre_movie_array, chosenItem) { dialog, which ->
                    chosenItem = which
                }
            .show()
        }
    }
}