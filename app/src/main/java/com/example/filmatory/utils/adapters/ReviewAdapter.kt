package com.example.filmatory.utils.adapters

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.PersonScene
import com.example.filmatory.utils.items.PersonItem
import com.example.filmatory.utils.items.ReviewItem

class ReviewAdapter(private val arrayList: MutableList<ReviewItem>, private val context: Context) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.review_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.reviewAuthor.text = arrayList[position].reviewAuthor
        holder.reviewDate.text = arrayList[position].reviewDate
        holder.reviewOverview.text = arrayList[position].reviewOverview
        holder.reviewRating.numStars = arrayList[position].rating
        Glide.with(context)
            .load("https://picsum.photos/40")
            .error(R.drawable.placeholder_image)
            .fallback(R.drawable.placeholder_image)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.reviewAuthorAvatar)
        holder.reviewId = arrayList[position].reviewId
        holder.reviewAuthorId = arrayList[position].userId

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reviewAuthor: TextView = view.findViewById(R.id.review_author_name)
        val reviewDate: TextView = view.findViewById(R.id.review_date)
        val reviewOverview: TextView = view.findViewById(R.id.review_overview)
        val reviewAuthorAvatar: ImageView = view.findViewById(R.id.review_author_img)
        val reviewRating: RatingBar = view.findViewById(R.id.rating_bar)
        var reviewAuthorId: String? = null
        var reviewId: String? = null
    }
}