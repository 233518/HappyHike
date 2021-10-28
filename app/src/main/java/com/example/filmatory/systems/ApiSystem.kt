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
import com.google.gson.GsonBuilder
import okhttp3.FormBody

/**
 * ApiSystem handles the interaction with the API
 *
 */
class ApiSystem : OnApiRequestFinishedListener {
    private var api = Api()
        get() = field

    /** All the GET requests */

    fun requestApprovedReviewById(id : String, function: (approvedReview : ApprovedReview) -> Unit) {
        api.runRequestGet("/review/approved/get/$id", this, 1, function as (Any) -> Unit);
    }
    fun requestDeniedReviewById(id : String, function: (deniedReview : DeniedReview) -> Unit) {
        api.runRequestGet("/review/denied/get/$id", this, 2, function as (Any) -> Unit);
    }
    fun requestPendingReviewById(id : String, function: (pendingReview : PendingReview) -> Unit) {
        api.runRequestGet("/review/pending/get/$id", this, 3, function as (Any) -> Unit);
    }
    fun requestUser(id : String, function: (user : User) -> Unit) {
        api.runRequestGet("/user/get/$id", this, 4, function as (Any) -> Unit);
    }
    fun requestMovie(id : String, function: (movie : Movie) -> Unit) {
        api.runRequestGet("/movie/get/$id", this, 5, function as (Any) -> Unit);
    }
    fun requestMovieFrontpage(function: (movieFrontPage : MovieFrontpage) -> Unit) {
        api.runRequestGet("/movie/frontpage", this, 6, function as (Any) -> Unit);
    }
    fun requestTV(id : String, function: (tv : Tv) -> Unit) {
        api.runRequestGet("/tv/get/$id", this, 7, function as (Any) -> Unit);
    }
    fun requestTvFrontpage(function: (tvFrontPage : TvFrontpage) -> Unit) {
        api.runRequestGet("/tv/frontpage", this, 8, function as (Any) -> Unit);
    }
    fun requestMovieUpcoming(function: (upcomingMovies : UpcomingMovies) -> Unit) {
        api.runRequestGet("/movie/upcomingmovies", this, 9, function as (Any) -> Unit);
    }
    fun requestMovies(function: (movies : Movies) -> Unit) {
        api.runRequestGet("/movie/movies", this, 10, function as (Any) -> Unit);
    }
    fun requestTvs(function: (tvs : Tvs) -> Unit) {
        api.runRequestGet("/tv/tvs", this, 11, function as (Any) -> Unit);
    }
    fun requestTvsUpcoming(function: (upcomingTvs : UpcomingTvs) -> Unit) {
        api.runRequestGet("/tv/upcomingtvs", this, 12, function as (Any) -> Unit);
    }
    fun requestAllLists(function: (lists : Lists) -> Unit) {
        api.runRequestGet("/lists/get", this, 13, function as (Any) -> Unit);
    }
    fun requestList(id : String, function: (list : List) -> Unit) {
        api.runRequestGet("/list/get/$id", this, 14, function as (Any) -> Unit);
    }
    fun requestPerson(id : String, function: (person : Person) -> Unit) {
        api.runRequestGet("/person/get/$id", this, 15, function as (Any) -> Unit);
    }
    fun requestUserFavorites(id : String, function: (favorites : Favorites) -> Unit) {
        api.runRequestGet("/user/get/favorites/$id", this, 16, function as (Any) -> Unit);
    }
    fun requestUserWatchlist(id : String, function: (watchlist : Watchlist) -> Unit) {
        api.runRequestGet("/user/get/watchlist/$id", this, 17, function as (Any) -> Unit);
    }
    fun requestUserLists(id : String, function: (userLists : UserLists) -> Unit) {
        api.runRequestGet("/user/get/lists/$id", this, 18, function as (Any) -> Unit);
    }
    fun requestMovieWatchProviders(id : String, function: (movieWatchProviders : MovieWatchProviders) -> Unit) {
        api.runRequestGet("/movie/get/watch/providers/$id", this, 19, function as (Any) -> Unit);
    }
    fun requestTvWatchProviders(id : String, function: (tvWatchProviders : TvWatchProviders) -> Unit) {
        api.runRequestGet("/tv/get/watch/providers/$id", this, 20, function as (Any) -> Unit);
    }

    /** All the POST requests */

    fun postUser(uid : String, function: (string : String?) -> Unit) {
        val formBody = FormBody.Builder()
            .add("uid", uid)
            .build()
        api.runRequestPostForm("/user/new", formBody,this, 1, function as (Any) -> Unit);
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
            else -> {
                print("Something went wrong, cant find requestId")
            }
        }
    }

    override fun onErrorRequest() {
        TODO("Not yet implemented")
    }
}