package com.example.filmatory.utils

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.MovieScene


class RecyclerViewAdapter(private val arrayList: MutableList<MediaItem>, private val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.media_item_container, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = arrayList[position].title
        holder.itemDate.text = arrayList[position].date
        Glide.with(this.context)
            .load(arrayList[position].image)
            .into(holder.itemImage)

        holder.itemView.setOnClickListener {
            val model = arrayList[position]
            val movieTitle: String = model.title
            val movieImage: String = model.image
            val movieDate: String = model.date
            val movieId: Int = model.id
            val intent = Intent(context, MovieScene::class.java)
            intent.putExtra("movieTitle", movieTitle)
            intent.putExtra("moviePoster", movieImage)
            intent.putExtra("movieDate", movieDate)
            intent.putExtra("movieId", movieId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.media_image)
        val itemTitle: TextView = view.findViewById(R.id.media_title)
        val itemDate: TextView = view.findViewById(R.id.media_date)
        val itemId: Int? = null
    }

}