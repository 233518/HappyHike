package com.example.filmatory.systems

import com.example.filmatory.api.*
import com.example.filmatory.api.data.lists.List
import com.example.filmatory.api.data.lists.Lists
import com.example.filmatory.api.data.movie.*
import com.example.filmatory.api.data.movie.MovieWatchProviders
import com.example.filmatory.api.data.person.Person
import com.example.filmatory.api.data.review.ApprovedReview
import com.example.filmatory.api.data.review.DeniedReview
import com.example.filmatory.api.data.review.PendingReview
import com.example.filmatory.api.data.tv.*
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.User
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.errors.BaseError
import com.google.gson.GsonBuilder
import okhttp3.FormBody

/**
 * ApiSystem handles the interaction with the API
 *
 */
class ApiSystem : OnApiRequestFinishedListener {
    private var api = Api()

    data class RequestBaseOptions(
        val id: String?,
        val uid: String?,
        val callbackSuccess: Any,
        val callbackFailure: (baseError: BaseError) -> Unit
    )

    /** All the GET requests */

    fun requestApprovedReviewById(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/review/approved/get/${requestOptions.id}", this, 1, requestOptions);
    }
    fun requestDeniedReviewById(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/review/denied/get/${requestOptions.id}", this, 2, requestOptions);
    }
    fun requestPendingReviewById(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/review/pending/get/${requestOptions.id}", this, 3, requestOptions);
    }
    fun requestUser(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/user/get/${requestOptions.uid}", this, 4, requestOptions);
    }
    fun requestMovie(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/get/${requestOptions.id}", this, 5, requestOptions);
    }
    fun requestMovieFrontpage(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/frontpage", this, 6, requestOptions);
    }
    fun requestTV(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/get/${requestOptions.id}", this, 7, requestOptions);
    }
    fun requestTvFrontpage(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/frontpage", this, 8, requestOptions);
    }
    fun requestMovieUpcoming(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/upcomingmovies", this, 9, requestOptions);
    }
    fun requestMovies(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/movies", this, 10, requestOptions);
    }
    fun requestTvs(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/tvs", this, 11, requestOptions);
    }
    fun requestTvsUpcoming(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/upcomingtvs", this, 12, requestOptions);
    }
    fun requestAllLists(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/lists/get", this, 13, requestOptions);
    }
    fun requestList(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/list/get/${requestOptions.id}", this, 14, requestOptions);
    }
    fun requestPerson(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/person/get/${requestOptions.id}", this, 15, requestOptions);
    }
    fun requestUserFavorites(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/user/get/favorites/${requestOptions.uid}", this, 16, requestOptions);
    }
    fun requestUserWatchlist(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/user/get/watchlist/${requestOptions.uid}", this, 17, requestOptions);
    }
    fun requestUserLists(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/user/get/lists/${requestOptions.uid}", this, 18, requestOptions);
    }
    fun requestMovieWatchProviders(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/get/watch/providers/${requestOptions.id}", this, 19, requestOptions);
    }
    fun requestTvWatchProviders(requestOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/get/watch/providers/${requestOptions.id}", this, 20, requestOptions);
    }

    /** All the POST requests */

    fun postUser(uid : String, function: (string : String?) -> Unit) {
        val formBody = FormBody.Builder()
            .add("uid", uid)
            .build()
        api.runRequestPostForm("/user/new", formBody,this, 1, function as (Any) -> Unit)
    }

    fun postUserUsername(uid : String, username : String, function: (string : String?) -> Unit) {
        val formBody = FormBody.Builder()
            .add("uid", uid)
            .add("username", username)
            .build()
        api.runRequestPostForm("/user/update/username", formBody, this, 2, function as (Any) -> Unit)
    }

    fun postUserAddMovieFavorite(uid : String, movieId : String, function: (string : String?) -> Unit) {
        val formBody = FormBody.Builder()
            .add("uid", uid)
            .add("movieId", movieId)
            .build()
        api.runRequestPostForm("/user/add/movie/favorite", formBody, this, 3, function as (Any) -> Unit)
    }

    fun postUserRemoveMovieFavorite(uid : String, movieId : String, function: (string : String?) -> Unit) {
        val formBody = FormBody.Builder()
            .add("uid", uid)
            .add("movieId", movieId)
            .build()
        api.runRequestPostForm("/user/remove/movie/favorite", formBody, this, 4, function as (Any) -> Unit)
    }

    fun postUserAddTvFavorite(uid : String, tvId : String, function: (string : String?) -> Unit) {
        val formBody = FormBody.Builder()
            .add("uid", uid)
            .add("tvId", tvId)
            .build()
        api.runRequestPostForm("/user/add/tv/favorite", formBody, this, 5, function as (Any) -> Unit)
    }

    fun postUserRemoveTvFavorite(uid : String, tvId : String, function: (string : String?) -> Unit) {
        val formBody = FormBody.Builder()
            .add("uid", uid)
            .add("tvId", tvId)
            .build()
        api.runRequestPostForm("/user/remove/tv/favorite", formBody, this, 6, function as (Any) -> Unit)
    }

    fun postUserAddWatchlist(uid : String, mediaId : String, type : String, function: (string : String?) -> Unit) {
        val formBody = FormBody.Builder()
            .add("uid", uid)
            .add("mediaId", mediaId)
            .add("mediaType", type)
            .build()
        api.runRequestPostForm("/user/add/watchlist", formBody, this, 7, function as (Any) -> Unit)
    }

    fun postUserRemoveWatchlist(uid : String, mediaId : String, type : String, function: (string : String?) -> Unit) {
        val formBody = FormBody.Builder()
            .add("uid", uid)
            .add("mediaId", mediaId)
            .add("mediaType", type)
            .build()
        api.runRequestPostForm("/user/remove/watchlist", formBody, this, 8, function as (Any) -> Unit)
    }
    override fun onSuccessRequestGet(result: String?, requestId: Int, function: (any : Any) -> Unit) {
        val gson = GsonBuilder().create()
        when (requestId) {
            1 -> function(gson.fromJson(result, ApprovedReview::class.java))
            2 -> function(gson.fromJson(result, DeniedReview::class.java))
            3 -> function(gson.fromJson(result, PendingReview::class.java))
            4 -> function(gson.fromJson(result, User::class.java))
            5 -> function(gson.fromJson(result, Movie::class.java))
            6 -> function(gson.fromJson(result, MovieFrontpage::class.java))
            7 -> function(gson.fromJson(result, Tv::class.java))
            8 -> function(gson.fromJson(result, TvFrontpage::class.java))
            9 -> function(gson.fromJson(result, UpcomingMovies::class.java))
            10 -> function(gson.fromJson(result, Movies::class.java))
            11 -> function(gson.fromJson(result, Tvs::class.java))
            12 -> function(gson.fromJson(result, UpcomingTvs::class.java))
            13 -> function(gson.fromJson(result, Lists::class.java))
            14 -> function(gson.fromJson(result, List::class.java))
            15 -> function(gson.fromJson(result, Person::class.java))
            16 -> function(gson.fromJson(result, Favorites::class.java))
            17 -> function(gson.fromJson(result, Watchlist::class.java))
            18 -> function(gson.fromJson(result, UserLists::class.java))
            19 -> function(gson.fromJson(result, MovieWatchProviders::class.java))
            20 -> function(gson.fromJson(result, TvWatchProviders::class.java))
            else -> { // Note the block
                print("Something went wrong, cant find requestId")
            }
        }
    }

    override fun onSuccessRequestPost(result: String?, requestId: Int, function: (any: Any) -> Unit) {
        when(requestId) {
            1 -> function(result as (Any))
            2 -> function(result as (Any))
            3 -> function(result as (Any))
            4 -> function(result as (Any))
            5 -> function(result as (Any))
            6 -> function(result as (Any))
            7 -> function(result as (Any))
            8 -> function(result as (Any))
            else -> {
                print("Something went wrong, cant find requestId")
            }
        }
    }

    override fun onErrorRequest() {
        TODO("Not yet implemented")
    }
}