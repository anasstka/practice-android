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
import com.example.cvetkovapracticenew.network.models.MessageResponse
import com.example.cvetkovapracticenew.network.models.MoviesResponse
import com.example.cvetkovapracticenew.presentation.activities.ChatActivity

class ChatAdapter(val messages: MessageResponse) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTextMessage: TextView
        var tvTextInfoAboutMessage: TextView
        var ivAvatar: ImageView

        init {
            tvMovieTitle = view.findViewById(R.id.iv)
            ivMoviePoster = view.findViewById(R.id.iv_moviePoster)
        }
    }

    //
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//        val itemView: View = LayoutInflater
//            .from(parent.context)
//            .inflate(
//                R.layout.movie_item,
//                parent,
//                false
//            )
//        return MovieViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        val movie = movies[position]
//
//        holder.tvMovieTitle.text = movie.name
//        holder.ivMoviePoster.load("http://cinema.areas.su/up/images/" + movie.posterUrl)
//
//        holder.itemView.setOnClickListener { view ->
//            val intent = Intent(view.context, ChatActivity::class.java)
//            intent.putExtra("movieId", movie.movieId)
//            intent.putExtra("movieName", movie.name)
//            view.context.startActivity(intent)
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return movies.size
//    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.movie_item,
                parent,
                false
            )
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}