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
import com.example.filmatory.scenes.activities.ListScene
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.scenes.activities.TvScene

class ListsAdapter(private val arrayList: MutableList<ListItem>, private val context: Context) : RecyclerView.Adapter<ListsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.activity_lists_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.itemTitle.text = arrayList[position].list_name
        holder.itemAuthor.text = arrayList[position].list_author
        holder.itemMovies.text = arrayList[position].list_total_movies
        holder.itemTvs.text = arrayList[position].list_total_tv
        Glide.with(this.context)
            .load(arrayList[position].image)
            .error(R.drawable.placeholder_image)
            .fallback(R.drawable.placeholder_image)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.itemImage)
        holder.itemId = arrayList[position].list_id

        holder.itemView.setOnClickListener {
            val model = arrayList[position]
            val listId: String = model.list_id
            val intent = Intent(context, ListScene::class.java)
            intent.putExtra("listId", listId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.list_image)
        val itemTitle: TextView = view.findViewById(R.id.list_title)
        val itemAuthor: TextView = view.findViewById(R.id.list_author)
        val itemMovies: TextView = view.findViewById(R.id.list_total_movies)
        val itemTvs: TextView = view.findViewById(R.id.list_total_tvs)
        var itemId: String? = null
    }

}