package com.example.filmatory.utils.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.MovieScene
import com.example.filmatory.scenes.activities.TvScene
import com.example.filmatory.utils.items.MediaModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DataAdapter(var context: Context, var arrayList: ArrayList<MediaModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_MOVIE = 1
        const val TYPE_TV = 2
        const val TYPE_MOVIE_SLIDER = 3
        const val TYPE_TV_SLIDER = 4
        const val TYPE_ACCINFO_MOVIE = 5
        const val TYPE_ACCINFO_TV = 6
        const val TYPE_SEARCH_MOVIE = 7
        const val TYPE_SEARCH_TV = 8
    }

    private inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.media_image)
        val itemTitle: TextView = itemView.findViewById(R.id.media_title)
        val itemDate: TextView = itemView.findViewById(R.id.media_date)
        var movieId: Int? = null
        fun bind(position: Int) {
            val viewmodel = arrayList[position]
            itemTitle.text = viewmodel.itemTitle
            itemDate.text = viewmodel.itemDate
            Glide.with(context)
                .load(viewmodel.itemImage)
                .error(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(itemImage)
            itemView.setOnClickListener {
                movieId = viewmodel.itemId
                val intent = Intent(context, MovieScene::class.java)
                intent.putExtra("movieId", movieId)
                context.startActivity(intent)
            }
        }
    }

    private inner class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.media_image)
        val itemTitle: TextView = itemView.findViewById(R.id.media_title)
        val itemDate: TextView = itemView.findViewById(R.id.media_date)
        var tvId: Int? = null
        fun bind(position: Int) {
            val viewmodel = arrayList[position]
            itemTitle.text = viewmodel.itemTitle
            itemDate.text = viewmodel.itemDate
            Glide.with(context)
                .load(viewmodel.itemImage)
                .error(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(itemImage)
            itemView.setOnClickListener {
                tvId = viewmodel.itemId
                val intent = Intent(context, TvScene::class.java)
                intent.putExtra("tvId", tvId)
                context.startActivity(intent)
            }
        }
    }

    private inner class TvSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.slider_image)
        val itemTitle: TextView = itemView.findViewById(R.id.slider_title)
        val itemDate: TextView = itemView.findViewById(R.id.slider_date)
        var tvId: Int? = null
        fun bind(position: Int) {
            val viewmodel = arrayList[position]
            itemTitle.text = viewmodel.itemTitle
            itemDate.text = viewmodel.itemDate
            Glide.with(context)
                .load(viewmodel.itemImage)
                .error(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(itemImage)
            itemView.setOnClickListener {
                tvId = viewmodel.itemId
                val intent = Intent(context, TvScene::class.java)
                intent.putExtra("tvId", tvId)
                context.startActivity(intent)
            }
        }
    }

    private inner class MovieSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.slider_image)
        val itemTitle: TextView = itemView.findViewById(R.id.slider_title)
        val itemDate: TextView = itemView.findViewById(R.id.slider_date)
        var movieId: Int? = null
        fun bind(position: Int) {
            val viewmodel = arrayList[position]
            itemTitle.text = viewmodel.itemTitle
            itemDate.text = viewmodel.itemDate
            Glide.with(context)
                .load(viewmodel.itemImage)
                .error(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(itemImage)
            itemView.setOnClickListener {
                movieId = viewmodel.itemId
                val intent = Intent(context, MovieScene::class.java)
                intent.putExtra("movieId", movieId)
                context.startActivity(intent)
            }
        }
    }

    private inner class MovieAccinfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.media_image)
        val itemTitle: TextView = itemView.findViewById(R.id.media_title)
        val itemDate: TextView = itemView.findViewById(R.id.media_date)
        var movieId: Int? = null
        fun bind(position: Int) {
            val viewmodel = arrayList[position]
            itemTitle.text = viewmodel.itemTitle
            itemDate.text = viewmodel.itemDate
            movieId = viewmodel.itemId
            Glide.with(context)
                .load(viewmodel.itemImage)
                .error(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(itemImage)
            itemView.setOnClickListener {
                val intent = Intent(context, MovieScene::class.java)
                intent.putExtra("movieId", movieId)
                context.startActivity(intent)
            }
            itemView.setOnLongClickListener {
                val menuItems = arrayOf("Delete")
                val chosenList = -1
                MaterialAlertDialogBuilder(context)
                    .setTitle(context.resources.getString(R.string.accinfo_mediamenu))
                    .setNeutralButton(context.resources.getString(R.string.cancel_btn)) { dialog, which ->

                    }
                    .setPositiveButton(context.resources.getString(R.string.confirm_btn)) { dialog, which ->
                        println(movieId)
                    }
                    .setSingleChoiceItems(menuItems, chosenList) { dialog, which ->
                        println(movieId)
                    }
                    .show()
                return@setOnLongClickListener true
            }
        }
    }

    private inner class TvAccinfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.media_image)
        val itemTitle: TextView = itemView.findViewById(R.id.media_title)
        val itemDate: TextView = itemView.findViewById(R.id.media_date)
        var tvId: Int? = null
        fun bind(position: Int) {
            val viewmodel = arrayList[position]
            itemTitle.text = viewmodel.itemTitle
            itemDate.text = viewmodel.itemDate
            tvId = viewmodel.itemId
            Glide.with(context)
                .load(viewmodel.itemImage)
                .error(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(itemImage)
            itemView.setOnClickListener {
                val intent = Intent(context, TvScene::class.java)
                intent.putExtra("tvId", tvId)
                context.startActivity(intent)
            }
            itemView.setOnLongClickListener {
                val menuItems = arrayOf("Delete")
                val chosenList = -1
                MaterialAlertDialogBuilder(context)
                    .setTitle(context.resources.getString(R.string.accinfo_mediamenu))
                    .setNeutralButton(context.resources.getString(R.string.cancel_btn)) { dialog, which ->

                    }
                    .setPositiveButton(context.resources.getString(R.string.confirm_btn)) { dialog, which ->
                        println(tvId)
                    }
                    .setSingleChoiceItems(menuItems, chosenList) { dialog, which ->
                        println(tvId)
                    }
                    .show()
                return@setOnLongClickListener true
            }
        }
    }

    private inner class MovieSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.search_image)
        val itemTitle: TextView = itemView.findViewById(R.id.search_title)
        val itemOverview: TextView = itemView.findViewById(R.id.search_overview)
        var movieId: Int? = null
        fun bind(position: Int) {
            val viewmodel = arrayList[position]
            itemTitle.text = viewmodel.itemTitle
            itemOverview.text = viewmodel.itemDate
            movieId = viewmodel.itemId
            Glide.with(context)
                .load(viewmodel.itemImage)
                .error(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(itemImage)
            itemView.setOnClickListener {
                val intent = Intent(context, MovieScene::class.java)
                intent.putExtra("movieId", movieId)
                context.startActivity(intent)
            }
        }
    }

    private inner class TvSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.search_image)
        val itemTitle: TextView = itemView.findViewById(R.id.search_title)
        val itemOverview: TextView = itemView.findViewById(R.id.search_overview)
        var tvId: Int? = null
        fun bind(position: Int) {
            val viewmodel = arrayList[position]
            itemTitle.text = viewmodel.itemTitle
            itemOverview.text = viewmodel.itemDate
            tvId = viewmodel.itemId
            Glide.with(context)
                .load(viewmodel.itemImage)
                .error(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(itemImage)
            itemView.setOnClickListener {
                val intent = Intent(context, TvScene::class.java)
                intent.putExtra("tvId", tvId)
                context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_MOVIE -> MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.media_item_container, parent, false))
            TYPE_TV -> TvViewHolder(LayoutInflater.from(context).inflate(R.layout.media_item_container, parent, false))
            TYPE_MOVIE_SLIDER -> MovieSliderViewHolder(LayoutInflater.from(context).inflate(R.layout.slider_item_container, parent, false))
            TYPE_TV_SLIDER -> TvSliderViewHolder(LayoutInflater.from(context).inflate(R.layout.slider_item_container, parent, false))
            TYPE_ACCINFO_MOVIE -> MovieAccinfoViewHolder(LayoutInflater.from(context).inflate(R.layout.media_item_container, parent, false))
            TYPE_ACCINFO_TV -> TvAccinfoViewHolder(LayoutInflater.from(context).inflate(R.layout.media_item_container, parent, false))
            TYPE_SEARCH_MOVIE -> MovieSearchViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_search_item, parent, false))
            TYPE_SEARCH_TV -> TvSearchViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_search_item, parent, false))
            else -> {
                TvViewHolder(LayoutInflater.from(context).inflate(R.layout.media_item_container, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(arrayList[position].viewType){
            TYPE_MOVIE -> (holder as MovieViewHolder).bind(position)
            TYPE_TV -> (holder as TvViewHolder).bind(position)
            TYPE_MOVIE_SLIDER -> (holder as MovieSliderViewHolder).bind(position)
            TYPE_TV_SLIDER -> (holder as TvSliderViewHolder).bind(position)
            TYPE_ACCINFO_MOVIE -> (holder as MovieAccinfoViewHolder).bind(position)
            TYPE_ACCINFO_TV -> (holder as TvAccinfoViewHolder).bind(position)
            TYPE_SEARCH_MOVIE -> (holder as MovieSearchViewHolder).bind(position)
            TYPE_SEARCH_TV -> (holder as TvSearchViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return arrayList[position].viewType
    }
}
