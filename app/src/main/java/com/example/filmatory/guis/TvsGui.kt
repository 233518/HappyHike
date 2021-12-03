package com.example.filmatory.guis

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.TvsController
import com.example.filmatory.scenes.activities.TvsScene

class TvsGui(private val tvsScene: TvsScene, private val tvsController: TvsController) {
    var tvsRecyclerView: RecyclerView = tvsScene.findViewById(R.id.recyclerView)
    var spinner : Spinner = tvsScene.findViewById(R.id.filter_spinner)

    init {
        ArrayAdapter.createFromResource(tvsScene, R.array.filter_array, android.R.layout.simple_spinner_dropdown_item).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.visibility = View.VISIBLE
            spinner.adapter = adapter
        }

        //Pass the scene to the listener that implements OnItemSelectedListener
        spinner.onItemSelectedListener = tvsScene
    }
}