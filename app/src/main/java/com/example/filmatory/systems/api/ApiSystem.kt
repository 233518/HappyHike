package com.example.filmatory.systems.api

import com.example.filmatory.api.*
import com.google.gson.GsonBuilder

class ApiSystem : OnApiRequestFinishedListener {
    private var api = Api()
        get() = field

    private lateinit var approvedReview: ApprovedReview
    private lateinit var pendingReview: PendingReview
    private lateinit var deniedReview: DeniedReview
    private lateinit var movieFrontpageItem: MovieFrontpageItem
    private lateinit var tvFrontpageItem: TvFrontpageItem

    fun requestApprovedReviewById(id : String, function: (apiRespons : ApiRespons) -> Unit) {
        api.runRequest("/review/approved/get/$id", this, 1, function);
    }
    fun requestDeniedReviewById(id : String, function: (apiRespons : ApiRespons) -> Unit) {
        api.runRequest("/review/denied/get/$id", this, 2, function);
    }
    fun requestPendingReviewById(id : String, function: (apiRespons : ApiRespons) -> Unit) {
        api.runRequest("/review/pending/get/$id", this, 3, function);
    }

    override fun onSuccessRequest(result: String?, requestId: Int?, function: (apiRespons : ApiRespons) -> Unit) {
        val gson = GsonBuilder().create()
        when (requestId) {
            1 -> {
                approvedReview = gson.fromJson(result, ApprovedReview::class.java)
                function(approvedReview);
            }
            2 -> deniedReview = gson.fromJson(result, DeniedReview::class.java)
            3 -> pendingReview = gson.fromJson(result, PendingReview::class.java)
            else -> { // Note the block
                print("Something went wrong, cant find requestId")
            }
        }
    }

    override fun onErrorRequest() {
        TODO("Not yet implemented")
    }
}