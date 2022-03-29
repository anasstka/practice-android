package com.example.app_wear.presentation.activities

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_wear.R
import com.example.app_wear.network.ApiHandler
import com.example.app_wear.network.ApiService
import com.example.app_wear.network.models.MoviesResponse
import com.example.app_wear.presentation.adapters.MovieAdapter
import com.example.app_wear.presentation.view.Dialog
import kotlinx.android.synthetic.main.activity_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    var service: ApiService = ApiHandler.instance.service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        rv_movies.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        getMovieCover()
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
                        if (movies != null)
                            rv_movies.adapter = MovieAdapter(movies)
                    } else {
                        Dialog(this@MoviesActivity, "Проблемы при загрузке данных")
                    }
                }

                override fun onFailure(call: Call<List<MoviesResponse>>, t: Throwable) {
                    Dialog(this@MoviesActivity, "Проблемы при загрузке данных")
                }
            })
        }
    }
}