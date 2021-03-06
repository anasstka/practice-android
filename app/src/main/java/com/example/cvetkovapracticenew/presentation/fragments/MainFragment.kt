package com.example.cvetkovapracticenew.presentation.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.MoviesResponse
import com.example.cvetkovapracticenew.presentation.adapters.MovieAdapter
import com.example.cvetkovapracticenew.presentation.view.dialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    // сервис для отправки запросов
    var service: ApiService = ApiHandler.instance.service

    lateinit var rvMovies: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(
            R.layout.fragment_main,
            container,
            false
        )

        val snapHelper = PagerSnapHelper()
        rvMovies = view.findViewById(R.id.rv_movies)
        rvMovies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        snapHelper.attachToRecyclerView(rvMovies)

        getMovieCover()

        return view
    }

    // метод для асинхронной отправки запроса для получения списка фильмов
    private fun getMovieCover() {
        AsyncTask.execute {
            service.getMovies().enqueue(object : Callback<List<MoviesResponse>> {
                override fun onResponse(
                    call: Call<List<MoviesResponse>>,
                    response: Response<List<MoviesResponse>>
                ) {
                    if (response.isSuccessful) {
                        val movies = response.body()
                        if (movies != null)
                            rvMovies.adapter = MovieAdapter(movies)
                    } else {
                        dialog(requireActivity(), "Ошибка сервера")
                    }
                }

                override fun onFailure(call: Call<List<MoviesResponse>>, t: Throwable) {
                    dialog(requireActivity(), "Ошибка")
                }
            })
        }
    }
}