package com.example.filmatory.scenes.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.CreateReviewController
import com.example.filmatory.scenes.SuperScene

/**
 * CreateReviewScene contains all the components for creating a create review page
 *
 */
class CreateReviewScene : SuperScene(), AdapterView.OnItemSelectedListener {
    private lateinit var createReviewController : CreateReviewController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_review)
        createReviewController = CreateReviewController(this)
    }

    override fun onItemSelected(p0: AdapterView<*>, view: View?, pos: Int, id: Long) {
        createReviewController.onNewSelected(p0.getItemAtPosition(pos))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        println("Should not happen")
    }
}