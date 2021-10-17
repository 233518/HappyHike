package com.example.filmatory.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.scenes.activities.TvScene


class TvSliderAdapter(private val arrayList: MutableList<MediaItem>, private val context: Context) : RecyclerView.Adapter<TvSliderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.slider_item_container, parent, false)
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
            val tvId: Int = model.id
            val intent = Intent(context, TvScene::class.java)
            intent.putExtra("tvId", tvId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.slider_image)
        val itemTitle: TextView = view.findViewById(R.id.slider_title)
        val itemDate: TextView = view.findViewById(R.id.slider_date)
        val itemId: Int? = null
    }

}