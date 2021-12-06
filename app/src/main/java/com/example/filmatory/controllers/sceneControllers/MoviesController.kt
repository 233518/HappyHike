package com.example.filmatory.controllers.sceneControllers

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmatory.R
import com.example.filmatory.api.data.movie.Movies
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.MoviesGui
import com.example.filmatory.scenes.activities.MoviesScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.collections.ArrayList
import androidx.core.widget.NestedScrollView


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

    private var moviesFilteredGenre : ArrayList<MediaModel> = ArrayList()

    init {
        apiSystem.requestMovies(RequestBaseOptions(null, null, ::moviesData, ::onFailure))
        apiSystem.requestMoviesFilterTitleAZ(RequestBaseOptions(null, null, ::moviesDataFilterTitle, ::onFailure))
        apiSystem.requestMoviesFilterDateDesc(RequestBaseOptions(null, null, ::moviesDataFilterDate, ::onFailure))

        moviesGui.nestedSv.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.


            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                moviesGui.count++
                println(moviesGui.count)
                // on below line we are making our progress bar visible.
                moviesGui.loadingBar.visibility = View.VISIBLE
                if (moviesGui.count < 6) {
                    println("HELLOOOO")
                    apiSystem.requestMovies(RequestBaseOptions(null, null, ::moviesData, ::onFailure))
                }
            }
        })
    }

    private fun moviesFilteredGenreData(movies: Movies){
        movies.forEach { item ->
            moviesFilteredGenre.add(MediaModel(DataAdapter.TYPE_MOVIE, item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        println(moviesFilteredGenre)
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
            val moviesAdapter = DataAdapter(moviesScene, this, moviesScene, moviesPopularDesc)
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

    private fun filteredGenre(){
        apiSystem.requestMovies(RequestBaseOptions(null, null, ::moviesFilteredGenreData, ::onFailure))
    }

    /**
     * Filtrerer filmene i valgt rekkefølge fra brukeren og oppdaterer adapteren
     *
     * @param arrayList : Dataen som skal vises
     */
    private fun moviesFilter(arrayList : ArrayList<MediaModel>){
        val moviesAdapter = DataAdapter(moviesScene, this, moviesScene, arrayList)
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
                        0 -> moviesFilter(moviesPopularDesc)
                        1 -> moviesFilter(moviesPopularAsc)
                        2 -> moviesFilter(moviesFilteredDateAsc)
                        3 -> moviesFilter(moviesFilteredDateDesc)
                        4 -> moviesFilter(moviesFilteredAZ)
                        5 -> moviesFilter(moviesFilteredZA)
                        else -> {
                            moviesFilter(moviesPopularDesc)
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
                    filteredGenre()
                }
                .setSingleChoiceItems(R.array.filter_genre_movie_array, chosenItem) { dialog, which ->
                    chosenItem = which
                }
            .show()
        }
    }
}