package com.example.filmatory.utils.adapters

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
import com.example.filmatory.scenes.activities.PersonScene
import com.example.filmatory.utils.items.PersonItem

/**
 * Custom adapter for actor slider items
 *
 * @property arrayList
 * @property context
 */
class PersonRecyclerViewAdapter(private val arrayList: MutableList<PersonItem>, private val context: Context) : RecyclerView.Adapter<PersonRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_slider_item, parent, false)
        )
    }

    /**
     * Updates the contents of the ViewHolder
     * Makes clickable so redirect to list
     *
     * @param holder : ViewHolder
     * @param position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.itemRealName.text = arrayList[position].realName
        holder.itemCharName.text = arrayList[position].charName
        Glide.with(context)
            .load(arrayList[position].image)
            .error(R.drawable.placeholder_image)
            .fallback(R.drawable.placeholder_image)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.itemImage)
        holder.itemId = arrayList[position].id

        holder.itemView.setOnClickListener {
            val model = arrayList[position]
            val personId: Int = model.id
            val intent = Intent(context, PersonScene::class.java)
            intent.putExtra("personId", personId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    /**
     * Sets the number of items the adapter will display
     *
     * @return Size of array
     */
    override fun getItemCount(): Int {
        return arrayList.size
    }

    /**
     * Binds data for the person to view and creates a direct reference
     *
     * @constructor
     *
     * @param itemView : The view to use
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.slider_image)
        val itemRealName: TextView = view.findViewById(R.id.slider_title)
        val itemCharName: TextView = view.findViewById(R.id.slider_date)
        var itemId: Int? = null
    }
}