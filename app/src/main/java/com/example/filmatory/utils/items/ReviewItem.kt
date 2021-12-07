package com.example.filmatory.utils.items

/**
 * Data class for review item
 *
 * @property reviewAuthor : Author
 * @property reviewAuthorAvatar : Avatar image path
 * @property reviewDate : Date
 * @property reviewOverview : Review text
 * @property rating : Review rating
 * @property userId : UserId
 * @property reviewId : ReviewId
 */
data class ReviewItem(val reviewAuthor: String,
                 val reviewAuthorAvatar: String,
                 val reviewDate: String,
                 val reviewOverview: String,
                 val rating: Int,
                 val userId: String,
                 val reviewId: String
)