package com.example.filmatory.utils



import android.content.Context
import androidx.cardview.widget.CardView

import android.widget.ImageView

import android.widget.TextView

import android.view.View

import androidx.recyclerview.widget.RecyclerView

import android.content.Intent

import android.view.LayoutInflater

import android.view.ViewGroup
import com.example.filmatory.R
import com.example.filmatory.scenes.MovieScene
import com.example.filmatory.scenes.MoviesScene
import com.example.filmatory.systems.media.Movie


class RecyclerViewAdapter(private val arrayList: MutableList<Movie>, private val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(movie: Movie){
            itemView.findViewById<TextView>(R.id.media_title).text = movie.title
            itemView.findViewById<TextView>(R.id.media_date).text = movie.date
            itemView.findViewById<ImageView>(R.id.media_img).setImageResource(movie.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener {
            val model = arrayList[position]
            var movieTitle: String = model.title
            var movieDate : String = model.date
            var movieImage: Int = model.image
            val intent = Intent(context, MovieScene::class.java)

            intent.putExtra("movieTitle", movieTitle)
            intent.putExtra("movieDate", movieDate)
            intent.putExtra("moviePoster", movieImage)
            context.startActivity(intent)
            }
        }


    override fun getItemCount(): Int {
        return arrayList.size
    }
}