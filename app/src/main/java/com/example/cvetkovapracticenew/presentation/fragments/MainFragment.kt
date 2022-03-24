package com.example.cvetkovapracticenew.presentation.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    var service: ApiService = ApiHandler.instance.service
    lateinit var tvMovieTitle: TextView
    lateinit var ivMoviePoster: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(
            R.layout.fragment_main,
            container,
            false
        )

        tvMovieTitle = view.findViewById(R.id.tv_movieTitle)
        ivMoviePoster = view.findViewById(R.id.iv_moviePoster)

        getMovieCover()

        return view
    }

    private fun getMovieCover() {
        AsyncTask.execute {
            service.getMovies().enqueue(object : Callback<List<MoviesResponse>> {
                override fun onResponse(
                    call: Call<List<MoviesResponse>>,
                    response: Response<List<MoviesResponse>>
                ) {
                    if (response.isSuccessful) {
                        val movies = response.body()
                        if (movies != null) {
                            tvMovieTitle.text = movies[1].name
                            ivMoviePoster.load("http://cinema.areas.su/up/images/" + movies[1].posterUrl)
                        }

                    } else if (response.code() == 400) {
                        Toast.makeText(
                            context,
                            "Проблемы при загрузке данных",
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }

                override fun onFailure(call: Call<List<MoviesResponse>>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Проблемы при загрузке данных",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            })
        }
    }
}