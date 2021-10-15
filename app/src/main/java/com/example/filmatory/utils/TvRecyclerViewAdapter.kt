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
import com.example.filmatory.scenes.activities.TvScene

class TvRecyclerViewAdapter(private val arrayList: MutableList<MediaItem>, private val context: Context) : RecyclerView.Adapter<TvRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.media_item_container, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.itemTitle.text = arrayList[position].title
        holder.itemDate.text = arrayList[position].date
        Glide.with(this.context)
            .load(arrayList[position].image)
            .error(R.drawable.placeholder_image)
            .fallback(R.drawable.placeholder_image)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.itemImage)
        holder.itemId = arrayList[position].id

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
        val itemImage: ImageView = view.findViewById(R.id.media_image)
        val itemTitle: TextView = view.findViewById(R.id.media_title)
        val itemDate: TextView = view.findViewById(R.id.media_date)
        var itemId: Int? = null
    }

}