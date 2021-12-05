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
import com.example.filmatory.api.data.search.Search
import com.example.filmatory.api.data.tv.*
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.User
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.errors.BaseError
import com.google.gson.GsonBuilder
import okhttp3.FormBody
import java.util.*

/**
 * ApiSystem handles the interaction with the API
 *
 */
class ApiSystem : OnApiRequestFinishedListener {
    private var api = Api()

    abstract class BaseOptions {
        abstract val id: String?
        abstract val uid: String?
        abstract val callbackSuccess: Any
        abstract val callbackFailure: (baseError: BaseError) -> Unit
    }

    data class RequestBaseOptions(
        override val id: String?,
        override val uid: String?,
        override val callbackSuccess: Any,
        override val callbackFailure: (baseError: BaseError) -> Unit,
    ) : BaseOptions()

    data class PostBaseOptions(
        override val id: String?,
        override val uid: String?,
        val params : Map<String, String>?,
        override val callbackSuccess: Any,
        override val callbackFailure: (baseError: BaseError) -> Unit,
    ) : BaseOptions()

    /** All the GET requests */

    fun requestTest(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/test", this, 0, requestBaseOptions)
    }
    fun requestApprovedReviewById(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/review/approved/get/${requestBaseOptions.id}", this, 1, requestBaseOptions)
    }
    fun requestDeniedReviewById(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/review/denied/get/${requestBaseOptions.id}", this, 2, requestBaseOptions)
    }
    fun requestPendingReviewById(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/review/pending/get/${requestBaseOptions.id}", this, 3, requestBaseOptions)
    }
    fun requestUser(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/user/get/${requestBaseOptions.uid}", this, 4, requestBaseOptions)
    }
    fun requestMovie(requestBaseOptions: RequestBaseOptions, language: String?) {
        api.runRequestGet("/movie/get/${requestBaseOptions.id}?languageCode=$language", this, 5, requestBaseOptions)
    }
    fun requestMovieFrontpageRecommend(requestBaseOptions: RequestBaseOptions, language: String?) {
        api.runRequestGet("/movie/frontpage/recommend?uid=${requestBaseOptions.uid}&languageCode=$language", this, 6, requestBaseOptions)
    }
    fun requestMovieFrontpageDiscover(requestBaseOptions: RequestBaseOptions, language: String?) {
        api.runRequestGet("/movie/frontpage/discover?languageCode=$language", this, 6, requestBaseOptions)
    }
    fun requestTV(requestBaseOptions: RequestBaseOptions,language: String?) {
        api.runRequestGet("/tv/get/${requestBaseOptions.id}?languageCode=$language", this, 7, requestBaseOptions)
    }
    fun requestTvFrontpageRecommend(requestBaseOptions: RequestBaseOptions, language: String?) {
        api.runRequestGet("/tv/frontpage/recommend?uid=${requestBaseOptions.uid}&languageCode=$language", this, 8, requestBaseOptions)
    }
    fun requestTvFrontpageDiscover(requestBaseOptions: RequestBaseOptions, language: String?) {
        api.runRequestGet("/tv/frontpage/discover?languageCode=$language", this, 8, requestBaseOptions)
    }
    fun requestMovieUpcoming(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/upcomingmovies", this, 9, requestBaseOptions)
    }
    fun requestMovies(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/movies", this, 10, requestBaseOptions)
    }
    fun requestMoviesFilterTitleAZ(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/movies/filter/title/az", this, 10, requestBaseOptions)
    }
    fun requestMoviesFilterTitleZA(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/movies/filter/title/za", this, 10, requestBaseOptions)
    }
    fun requestMoviesFilterDateAsc(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/movies/filter/date/asc", this, 10, requestBaseOptions)
    }
    fun requestMoviesFilterDateDesc(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/movies/filter/date/desc", this, 10, requestBaseOptions)
    }
    fun requestTvs(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/tvs", this, 11, requestBaseOptions)
    }
    fun requestTvsFilterTitleAZ(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/tvs/filter/title/az", this, 11, requestBaseOptions)
    }
    fun requestTvsFilterTitleZA(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/tvs/filter/title/za", this, 11, requestBaseOptions)
    }
    fun requestTvsFilterDateAsc(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/tvs/filter/date/asc", this, 11, requestBaseOptions)
    }
    fun requestTvsFilterDateDesc(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/tvs/filter/date/desc", this, 11, requestBaseOptions)
    }
    fun requestTvsUpcoming(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/upcomingtvs", this, 12, requestBaseOptions)
    }
    fun requestAllLists(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/lists/get", this, 13, requestBaseOptions)
    }
    fun requestList(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/list/get/${requestBaseOptions.id}", this, 14, requestBaseOptions)
    }
    fun requestPerson(requestBaseOptions: RequestBaseOptions, language: String?) {
        api.runRequestGet("/person/get/${requestBaseOptions.id}?languageCode=$language", this, 15, requestBaseOptions)
    }
    fun requestUserFavorites(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/user/get/favorites/${requestBaseOptions.uid}", this, 16, requestBaseOptions)
    }
    fun requestUserWatchlist(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/user/get/watchlist/${requestBaseOptions.uid}", this, 17, requestBaseOptions)
    }
    fun requestUserLists(requestBaseOptions: RequestBaseOptions, language: String?) {
        api.runRequestGet("/user/get/lists/${requestBaseOptions.uid}?languageCode=$language", this, 18, requestBaseOptions)
    }
    fun requestMovieWatchProviders(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/movie/get/watch/providers/${requestBaseOptions.id}", this, 19, requestBaseOptions)
    }
    fun requestTvWatchProviders(requestBaseOptions: RequestBaseOptions) {
        api.runRequestGet("/tv/get/watch/providers/${requestBaseOptions.id}", this, 20, requestBaseOptions)
    }
    fun requestSearch(requestBaseOptions: RequestBaseOptions, title: String, language: String?) {
        api.runRequestGet("/search/get/$title?languageCode=$language", this, 21, requestBaseOptions)
    }
    fun requestMovieReviews(requestBaseOptions: RequestBaseOptions, language: String?) {
        api.runRequestGet("/movie/get/reviews/${requestBaseOptions.id}", this, 22, requestBaseOptions)
    }
    fun requestTvReviews(requestBaseOptions: RequestBaseOptions, language: String?) {
        api.runRequestGet("/tv/get/reviews/${requestBaseOptions.id}", this, 23, requestBaseOptions)
    }

    /** All the POST requests */
    fun postUser(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("uid", postBaseOptions.uid)
            .build()
        api.runRequestPostForm("/user/new", formBody,this, 1, postBaseOptions)
    }

    fun postUserUsername(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("uid", postBaseOptions.uid)
            .add("username", postBaseOptions.params?.get("username"))
            .build()
        api.runRequestPostForm("/user/update/username", formBody, this, 2, postBaseOptions)
    }

    fun postUserAddMovieFavorite(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("uid", postBaseOptions.uid)
            .add("movieId", postBaseOptions.params?.get("movieId"))
            .build()
        api.runRequestPostForm("/user/add/movie/favorite", formBody, this, 3, postBaseOptions)
    }

    fun postUserRemoveMovieFavorite(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("uid", postBaseOptions.uid)
            .add("movieId", postBaseOptions.params?.get("movieId"))
            .build()
        api.runRequestPostForm("/user/remove/movie/favorite", formBody, this, 4, postBaseOptions)
    }

    fun postUserAddTvFavorite(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("uid", postBaseOptions.uid)
            .add("tvId", postBaseOptions.params?.get("tvId"))
            .build()
        api.runRequestPostForm("/user/add/tv/favorite", formBody, this, 5, postBaseOptions)
    }

    fun postUserRemoveTvFavorite(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("uid", postBaseOptions.uid)
            .add("tvId", postBaseOptions.params?.get("tvId"))
            .build()
        api.runRequestPostForm("/user/remove/tv/favorite", formBody, this, 6, postBaseOptions)
    }

    fun postUserAddWatchlist(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("uid", postBaseOptions.uid)
            .add("mediaId", postBaseOptions.params?.get("mediaId"))
            .add("mediaType", postBaseOptions.params?.get("mediaType"))
            .build()
        api.runRequestPostForm("/user/add/watchlist", formBody, this, 7, postBaseOptions)
    }

    fun postUserRemoveWatchlist(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("uid", postBaseOptions.uid)
            .add("mediaId", postBaseOptions.params?.get("mediaId"))
            .add("mediaType", postBaseOptions.params?.get("mediaType"))
            .build()
        api.runRequestPostForm("/user/remove/watchlist", formBody, this, 8, postBaseOptions)
    }

    fun postListAddMovie(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("movieId", postBaseOptions.params?.get("movieId"))
            .add("listId", postBaseOptions.params?.get("listId"))
            .build()
        api.runRequestPostForm("/list/add/movie", formBody, this, 9, postBaseOptions)
    }

    fun postListRemoveMovie(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("movieId", postBaseOptions.params?.get("movieId"))
            .add("listId", postBaseOptions.params?.get("listId"))
            .build()
        api.runRequestPostForm("/list/remove/movie", formBody, this, 10, postBaseOptions)
    }

    fun postListAddTv(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("tvId", postBaseOptions.params?.get("tvId"))
            .add("listId", postBaseOptions.params?.get("listId"))
            .build()
        api.runRequestPostForm("/list/add/tv", formBody, this, 11, postBaseOptions)
    }

    fun postListRemoveTv(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("tvId", postBaseOptions.params?.get("tvId"))
            .add("listId", postBaseOptions.params?.get("listId"))
            .build()
        api.runRequestPostForm("/list/remove/tv", formBody, this, 12, postBaseOptions)
    }

    fun postListCreate(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("uid", postBaseOptions.uid)
            .add("listName", postBaseOptions.params?.get("listName"))
            .build()
        api.runRequestPostForm("/list/create", formBody, this, 13, postBaseOptions)
    }

    fun postListDelete(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("listId", postBaseOptions.params?.get("listId"))
            .build()
        api.runRequestPostForm("/list/delete", formBody, this, 14, postBaseOptions)
    }
    fun postReviewMoviePending(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("data", postBaseOptions.params?.get("movie"))
            .add("type", "movie")
            .build()
        api.runRequestPostForm("/review/new", formBody, this, 15, postBaseOptions)
    }

    fun postReviewTvPending(postBaseOptions: PostBaseOptions) {
        val formBody = FormBody.Builder()
            .add("data", postBaseOptions.params?.get("tv"))
            .add("type", "tv")
            .build()
        api.runRequestPostForm("/review/new", formBody, this, 16, postBaseOptions)
    }

    override fun onSuccessRequestGet(result: String?, requestId: Int, function: (any : Any) -> Unit) {
        val gson = GsonBuilder().create()
        when (requestId) {
            0 -> function("Test")
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
            21 -> function(gson.fromJson(result, Search::class.java))
            22 -> function(gson.fromJson(result, MovieReviews::class.java))
            23 -> function(gson.fromJson(result, TvReviews::class.java))
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
            9 -> function(result as (Any))
            10 -> function(result as (Any))
            11 -> function(result as (Any))
            12 -> function(result as (Any))
            13 -> function(result as (Any))
            14 -> function(result as (Any))
            15 -> function(result as (Any))
            16 -> function(result as (Any))
            else -> {
                print("Something went wrong, cant find requestId")
            }
        }
    }
}