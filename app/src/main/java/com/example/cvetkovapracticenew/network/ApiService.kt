package com.example.cvetkovapracticenew.network

import com.example.cvetkovapracticenew.network.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/auth/register")
    fun register(@Body registrationBody: RegistrationBody): Call<ResponseBody>

    @POST("/auth/login")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>

    @GET("/movies")
    fun getMovies(
        @Query("filter")
        filter: String = "new"
    ): Call<List<MoviesResponse>>

    @GET("/user")
    fun getUser(): Call<List<UserResponse>>
}