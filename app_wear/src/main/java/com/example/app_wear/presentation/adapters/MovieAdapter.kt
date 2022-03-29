package com.example.app_wear.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.app_wear.R
import com.example.app_wear.network.models.MoviesResponse

class MovieAdapter(val movies: List<MoviesResponse>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvMovieTitle: TextView
        var ivMoviePoster: ImageView

        init {
            tvMovieTitle = view.findViewById(R.id.tv_movieTitle)
            ivMoviePoster = view.findViewById(R.id.iv_moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.movie_item,
                parent,
                false
            )
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.tvMovieTitle.text = movie.name
        holder.ivMoviePoster.load("http://cinema.areas.su/up/images/" + movie.posterUrl)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}