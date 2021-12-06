package com.example.filmatory.guis

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.CreateReviewController
import com.example.filmatory.scenes.activities.CreateReviewScene

class ReviewGui (createReviewScene: CreateReviewScene, private val createReviewController: CreateReviewController) {
    private var ratingDropdownList : Spinner = createReviewScene.findViewById(R.id.review_rating_spinner)
    private var reviewOverview : EditText = createReviewScene.findViewById(R.id.create_review_edittext)
    private var reviewSubmitBtn : Button = createReviewScene.findViewById(R.id.create_review_btn)
    private var reviewCancelBtn : Button = createReviewScene.findViewById(R.id.create_review_cancel_btn)

    init {
        ArrayAdapter.createFromResource(createReviewScene, R.array.rating_array, R.layout.spinner_item).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            ratingDropdownList.visibility = View.VISIBLE
            ratingDropdownList.adapter = adapter

            reviewCancelBtn.setOnClickListener {
                createReviewController.cancelCreateReview()

            }

            reviewSubmitBtn.setOnClickListener {
                val reviewText : String = reviewOverview.text.toString()
                createReviewController.submitCreateReview(reviewText)

            }
        }
        ratingDropdownList.onItemSelectedListener = createReviewScene
    }
}