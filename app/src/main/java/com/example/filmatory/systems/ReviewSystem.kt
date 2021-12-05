package com.example.filmatory.systems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.StartScene

class ReviewSystem(private val apiSystem: ApiSystem, private val snackbarSystem: SnackbarSystem, private val scene: AppCompatActivity) {

    fun addMovieToFavorites(uid : String, movieId : String){
        val params: HashMap<String, String> = HashMap()
        params["movieId"] = movieId

        apiSystem.postUserAddMovieFavorite(
            ApiSystem.PostBaseOptions(
                null,
                uid,
                params,
                ::newUserResponse,
                ::onFailure
            )
        )
    }

    fun addPendingReview(uid : String, mediaId : String, mediaType : String, reviewText : String, rating : String){
        val params: HashMap<String, String> = HashMap()
        params["text"] = reviewText
        params["stars"] = rating
        if(mediaType == "movie"){
            apiSystem.postReviewMoviePending(ApiSystem.PostBaseOptions(mediaId, uid, params, ::newUserResponse, ::onFailure))
        } else {
            apiSystem.postReviewTvPending(ApiSystem.PostBaseOptions(mediaId, uid, params, ::newUserResponse, ::onFailure))
        }
    }

    private fun newUserResponse(string : String?) {
        snackbarSystem.duration = 2000
        snackbarSystem.showSnackbarSuccess(string!!)
    }
    private fun onFailure(baseError: BaseError) {
        snackbarSystem.showSnackbarFailure(baseError.message, ::retry, "Home")
    }
    private fun retry() {
        val intent = Intent(scene, StartScene::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        scene.finish()
        scene.startActivity(intent)
    }
}