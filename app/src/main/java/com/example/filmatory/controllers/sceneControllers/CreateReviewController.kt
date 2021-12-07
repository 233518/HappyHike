package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.ReviewGui
import com.example.filmatory.scenes.activities.CreateReviewScene
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.scenes.activities.TvScene
import com.example.filmatory.systems.ReviewSystem

/**
 * CreateReviewController controls everything related to the create review page
 *
 * @property createReviewScene : The CreateReviewScene to use
 */
class CreateReviewController(private val createReviewScene: CreateReviewScene) : MainController(createReviewScene) {
    private val createReviewGui = ReviewGui(createReviewScene, this)
    private val reviewSystem = ReviewSystem(apiSystem, snackbarSystem, createReviewScene)
    private var intent: Intent = createReviewScene.intent

    private var mediaId = intent.getIntExtra("mediaId", 0)
    private var mediaType = intent.getStringExtra("mediaType")
    private var rating : Any = 0

    /**
     * Runs a method which creates a review
     *
     * @param reviewText : The review text
     */
    fun submitCreateReview(reviewText : String){
        reviewSystem.addPendingReview(createReviewScene.auth.currentUser!!.uid,
        mediaId.toString(),
            mediaType!!,
            reviewText,
            rating.toString()
    )}

    /**
     * Method to return the user to the movie or tv-show they are writing a review for.
     *
     */
    fun cancelCreateReview(){
        if(mediaType == "movie"){
            val intent = Intent(createReviewScene, MovieScene::class.java)
            intent.putExtra("movieId", mediaId)
            createReviewScene.finish()
            createReviewScene.startActivity(intent)
        } else {
            val intent = Intent(createReviewScene, TvScene::class.java)
            intent.putExtra("tvId", mediaId)
            createReviewScene.finish()
            createReviewScene.startActivity(intent)
        }
    }

    /**
     * Sets rating variable to the selected one in spinner
     *
     * @param itemAtPosition : Selected rating
     */
    fun onNewSelected(itemAtPosition: Any) {
        rating = itemAtPosition
    }
}