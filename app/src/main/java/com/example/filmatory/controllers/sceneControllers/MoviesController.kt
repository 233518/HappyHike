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

/**
 * MoviesController manipulates the MoviesScene gui
 *
 * @param moviesScene The MoviesScene to use
 */
class MoviesController(private val moviesScene: MoviesScene) : MainController(moviesScene) {
    private val moviesArrayList : ArrayList<MediaModel> = ArrayList()
    private val moviesRecyclerView: RecyclerView = moviesScene.findViewById(R.id.recyclerView)
    private val moviesAdapter = DataAdapter(moviesScene, moviesArrayList)
    private val spinner : Spinner = moviesScene.findViewById(R.id.filter_spinner)
    private val test : Array<String> = arrayOf("test", "test")

    init {
        apiSystem.requestMovies(RequestBaseOptions(null, null, ::moviesData, ::onFailure))
        moviesRecyclerView.layoutManager = GridLayoutManager(moviesScene, 2)
        moviesRecyclerView.adapter = moviesAdapter
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
     * Update the gui with data from API
     *
     * @param movies The response from API
     */
    private fun moviesData(movies: Movies){
        moviesScene.runOnUiThread(Runnable {
            movies.forEach{
                    item -> moviesArrayList.add(MediaModel(DataAdapter.TYPE_MOVIE, item.title, item.releaseDate,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + item.pictureUrl, item.id))
            }
            moviesAdapter.notifyDataSetChanged()
        })
    }

    fun onNewSelected(itemAtPosition: Any) {
        //TODO: MAKE ADAPTER UPDATE
    }
}