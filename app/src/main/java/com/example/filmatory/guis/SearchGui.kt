package com.example.filmatory.guis

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.SearchController
import com.example.filmatory.scenes.activities.SearchScene

class SearchGui(private val searchScene: SearchScene, private val searchController: SearchController) {
    var dropdown: Spinner = searchScene.findViewById(R.id.spinner1)
    var resultRecyclerView: RecyclerView = searchScene.findViewById(R.id.recyclerView)
    init {
        ArrayAdapter.createFromResource(searchScene, R.array.media_array, android.R.layout.simple_spinner_dropdown_item).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            dropdown.adapter = adapter
        }
        dropdown.onItemSelectedListener = searchScene
    }
}