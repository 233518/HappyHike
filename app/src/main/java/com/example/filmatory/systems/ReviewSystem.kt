package com.example.filmatory.systems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.StartScene

/**
 * ReviewSystem takes care of review actions
 *
 * @property apiSystem The api system to use
 * @property snackbarSystem The snackbar system to use
 * @property scene The scene to use
 */
class ReviewSystem(private val apiSystem: ApiSystem, private val snackbarSystem: SnackbarSystem, private val scene: AppCompatActivity) {

    /**
     * Sends request to create a new review in database
     *
     * @param uid The user ID
     * @param mediaId The media ID
     * @param mediaType The media type
     * @param reviewText The review text
     * @param rating The review rating 1-5
     */
    fun addPendingReview(uid : String, mediaId : String, mediaType : String, reviewText : String, rating : String){
        val params: HashMap<String, String> = HashMap()
        params["text"] = reviewText
        params["stars"] = rating
        if(mediaType == "movie"){
            apiSystem.postReviewMoviePending(ApiSystem.PostBaseOptions(mediaId, uid, params, ::newPostResponse, ::onFailure))
        } else {
            apiSystem.postReviewTvPending(ApiSystem.PostBaseOptions(mediaId, uid, params, ::newPostResponse, ::onFailure))
        }
    }

    /**
     * Shows snackbar message of post result
     *
     * @param string The message
     */
    private fun newPostResponse(string : String?) {
        snackbarSystem.duration = 2000
        snackbarSystem.showSnackbarSuccess(string!!)
    }

    /**
     * Runs when API fails
     *
     * @param baseError The error
     */
    private fun onFailure(baseError: BaseError) {
        snackbarSystem.showSnackbarFailure(baseError.message, ::retry, "Home")
    }

    /**
     * Sends user back to start page
     *
     */
    private fun retry() {
        val intent = Intent(scene, StartScene::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        scene.finish()
        scene.startActivity(intent)
    }
}