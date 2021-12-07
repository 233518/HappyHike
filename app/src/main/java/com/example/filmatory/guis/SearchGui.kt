package com.example.filmatory.guis

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.SearchController
import com.example.filmatory.scenes.activities.SearchScene

/**
 * SearchGui contains all the gui elements for the search page
 *
 * @property searchScene The scene to use
 * @property searchController The controller to use
 */
class SearchGui(private val searchScene: SearchScene, private val searchController: SearchController) : AdapterView.OnItemSelectedListener {
    var dropdown: Spinner = searchScene.findViewById(R.id.spinner1)
    var resultRecyclerView: RecyclerView = searchScene.findViewById(R.id.recyclerView)
    init {
        ArrayAdapter.createFromResource(searchScene, R.array.media_array, android.R.layout.simple_spinner_dropdown_item).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            dropdown.adapter = adapter
        }
        dropdown.onItemSelectedListener = this
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        searchController.onNewSelected(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        println("This should not happen")
    }
}