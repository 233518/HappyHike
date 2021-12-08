package com.example.filmatory.controllers.sceneControllers

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
import androidx.core.widget.NestedScrollView

/**
 * MoviesController controls everything related to the movies page
 *
 * @param moviesScene The MoviesScene to use
 */
class MoviesController(private val moviesScene: MoviesScene) : MainController(moviesScene) {
    private val moviesGui = MoviesGui(moviesScene, this)
    private var tempMoviesArray : ArrayList<MediaModel> = ArrayList()
    private var movieHashMap : HashMap<String, ArrayList<MediaModel>> = HashMap()

    private var genreId : Int? = null
    private var position : Int = 0
    private val maxMovies : Int = 11
    private var maxIterations : Int = 0
    private var currentFilter : String = "moviesPopularDesc"

    init {
        apiSystem.requestMovies(RequestBaseOptions(null, null, ::moviesData, ::onFailure))
        apiSystem.requestMoviesFilterTitleAZ(RequestBaseOptions(null, null, ::moviesDataFilterTitle, ::onFailure))
        apiSystem.requestMoviesFilterDateDesc(RequestBaseOptions(null, null, ::moviesDataFilterDate, ::onFailure))

        moviesGui.nestedSv.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight && scrollY > oldScrollY) {
                    loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)
                }
            }
        }
    }

    /**
     * Method to load 10 by 10 movies as the user scrolls to bottom
     *
     * @param array : Array to be loaded
     */
    private fun loadMore(array : ArrayList<MediaModel>){
        moviesScene.runOnUiThread {
            val moviesAdapter = DataAdapter(moviesScene, this, moviesScene, tempMoviesArray)
            moviesGui.moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
            moviesGui.moviesRecyclerView.adapter = moviesAdapter
            if(maxIterations > position){
                for(i in position*(maxMovies+1)..(position+1)*maxMovies step 1){
                    tempMoviesArray.add(array[i])
                    moviesAdapter.notifyItemInserted(i)
                }
                position++
            } else if(maxIterations == position) {
                for(i in position*(maxMovies+1) until array.size step 1){
                    tempMoviesArray.add(array[i])
                    moviesAdapter.notifyItemInserted(i)
                }
                position++
            } else {
                moviesGui.disableLoadingBar()
            }
        }
    }

    /**
     * Sets data from API to Arraylists and displays popular movies descending on activity start
     *
     * @param movies The response from API
     */
    private fun moviesData(movies: Movies){
        val moviesPopularDesc : ArrayList<MediaModel> = ArrayList()
        movies.forEach { item ->
            moviesPopularDesc.add(MediaModel(DataAdapter.TYPE_MOVIE, item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        movieHashMap["moviesPopularDesc"] = moviesPopularDesc
        val moviesPopularAsc: ArrayList<MediaModel> = ArrayList(moviesPopularDesc)
        moviesPopularAsc.reverse()
        movieHashMap["moviesPopularAsc"] = moviesPopularAsc
        maxIterations = moviesPopularDesc.size / maxMovies
        loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)
    }

    /**
     * Sets data from API depending on which genre is chosen from genre filter
     *
     * @param movies The response from the API
     */
    private fun moviesFilteredGenreData(movies: Movies){
        val moviesFilteredGenre : ArrayList<MediaModel> = ArrayList()
        moviesFilteredGenre.clear()
        movies.forEach { item ->
            if(item.genre.contains(genreId)){
                moviesFilteredGenre.add(MediaModel(DataAdapter.TYPE_MOVIE, item.title, item.releaseDate, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
        }
        movieHashMap["moviesFilteredGenre"] = moviesFilteredGenre
        currentFilter = "moviesFilteredGenre"
        maxIterations = moviesFilteredGenre.size / maxMovies
        loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)

        if(moviesFilteredGenre.size < 3) moviesGui.disableLoadingBar()
    }

    /**
     * Sets data from API to Arraylists
     *
     * @param movies The response from API
     */
    private fun moviesDataFilterTitle(movies: Movies){
        val moviesFilteredAZ : ArrayList<MediaModel> = ArrayList()
        movies.forEach{
            item -> moviesFilteredAZ.add(MediaModel(DataAdapter.TYPE_MOVIE, item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        movieHashMap["moviesFilteredAZ"] = moviesFilteredAZ
        val moviesFilteredZA: ArrayList<MediaModel> = ArrayList(moviesFilteredAZ)
        moviesFilteredZA.reverse()
        movieHashMap["moviesFilteredZA"] = moviesFilteredZA
    }

    /**
     * Sets data from API to Arraylists
     *
     * @param movies The response from API
     */
    private fun moviesDataFilterDate(movies: Movies){
        val moviesFilteredDateAsc : ArrayList<MediaModel> = ArrayList()
        movies.forEach{
            item -> moviesFilteredDateAsc.add(MediaModel(DataAdapter.TYPE_MOVIE, item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
        }
        movieHashMap["moviesFilteredDateAsc"] = moviesFilteredDateAsc
        val moviesFilteredDateDesc: ArrayList<MediaModel> = ArrayList(moviesFilteredDateAsc)
        moviesFilteredDateDesc.reverse()
        movieHashMap["moviesFilteredDateDesc"] = moviesFilteredDateDesc
    }

    /**
     * Sets genreId and requests the movies to be filtered
     *
     * @param id : Id of the genre
     */
    private fun filteredGenre(id : Int){
        genreId = id
        apiSystem.requestMovies(RequestBaseOptions(null, null, ::moviesFilteredGenreData, ::onFailure))
    }

    /**
     * Opens a confirm dialog to choose filter
     * When filter is chosen, set the current filter and load it from the HashMap
     *
     */
    fun showFilterList(){
        moviesScene.runOnUiThread {
            var chosenItem: Int = -1
            MaterialAlertDialogBuilder(moviesScene)
                .setTitle(moviesScene.resources.getString(R.string.filter))
                .setNeutralButton(moviesScene.resources.getString(R.string.cancel_btn)) { dialog, which -> }
                .setPositiveButton(moviesScene.resources.getString(R.string.confirm_btn)) { dialog, which ->
                    tempMoviesArray.clear()
                    position = 0
                    when(chosenItem){
                        0 -> {
                            currentFilter = "moviesPopularDesc"
                            loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        1 -> {
                            currentFilter = "moviesPopularAsc"
                            loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        2 -> {
                            currentFilter = "moviesFilteredDateAsc"
                            loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        3 -> {
                            currentFilter = "moviesFilteredDateDesc"
                            loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        4 -> {
                            currentFilter = "moviesFilteredAZ"
                            loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        5 -> {
                            currentFilter = "moviesFilteredZA"
                            loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                        else -> {
                            currentFilter = "moviesPopularDesc"
                            loadMore(movieHashMap[currentFilter] as ArrayList<MediaModel>)
                        }
                    }
                }
                .setSingleChoiceItems(R.array.filter_array, chosenItem) { dialog, which ->
                    chosenItem = which
                }
            .show()
        }
    }

    /**
     * Opens a confirm dialog to choose which genre to filter by
     * When chosen, run a method to filter movies by genreId
     *
     */
    fun showGenreFilterList(){
        moviesScene.runOnUiThread {
            var chosenItem: Int = -1
            MaterialAlertDialogBuilder(moviesScene)
                .setTitle(moviesScene.resources.getString(R.string.filter_genre))
                .setNeutralButton(moviesScene.resources.getString(R.string.close_btn)) { dialog, which -> }
                .setPositiveButton(moviesScene.resources.getString(R.string.confirm_btn)) { dialog, which ->
                    tempMoviesArray.clear()
                    position = 0
                    when(chosenItem){
                        0 -> filteredGenre(28)
                        1 -> filteredGenre(12)
                        2 -> filteredGenre(16)
                        3 -> filteredGenre(35)
                        4 -> filteredGenre(80)
                        5 -> filteredGenre(99)
                        6 -> filteredGenre(18)
                        7 -> filteredGenre(10751)
                        8 -> filteredGenre(14)
                        9 -> filteredGenre(36)
                        10 -> filteredGenre(27)
                        11 -> filteredGenre(10402)
                        12 -> filteredGenre(9648)
                        13 -> filteredGenre(10749)
                        14 -> filteredGenre(878)
                        15 -> filteredGenre(10770)
                        16 -> filteredGenre(53)
                        17 -> filteredGenre(10752)
                        18 -> filteredGenre(37)
                    }
                }
                .setSingleChoiceItems(R.array.filter_genre_movie_array, chosenItem) { dialog, which ->
                    chosenItem = which
                }
            .show()
        }
    }
}