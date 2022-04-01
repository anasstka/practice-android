package com.example.cvetkovapracticenew.presentation.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.network.models.MoviesResponse
import com.example.cvetkovapracticenew.presentation.activities.ChatActivity

// адаптер для заполнение списка фильмами
class MovieAdapter(
    private val movies: List<MoviesResponse>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // viewholder для инициализации полей элемента списка и заполнение их данными
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvMovieTitle: TextView
        var ivMoviePoster: ImageView

        init {
            tvMovieTitle = view.findViewById(R.id.tv_movieTitle)
            ivMoviePoster = view.findViewById(R.id.iv_moviePoster)
        }
    }

    // инициализация макета интерфейса для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_movie,
                parent,
                false
            )
        return MovieViewHolder(itemView)
    }

    // заполнение viewholder данными
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.tvMovieTitle.text = movie.name
        holder.ivMoviePoster.load("http://cinema.areas.su/up/images/" + movie.posterUrl)

        // событие нажатие на фильм, переход на экран с чатом
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, ChatActivity::class.java)
            intent.putExtra("movieId", movie.movieId)
            intent.putExtra("movieName", movie.name)
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}