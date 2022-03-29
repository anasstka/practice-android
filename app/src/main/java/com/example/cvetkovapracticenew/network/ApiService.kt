package com.example.cvetkovapracticenew.network

import com.example.cvetkovapracticenew.network.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

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

    @GET("/chats/{chatId}/messages")
    fun getChatMessages(
        @Path("chatId")
        chatId: Int
    ): Call<List<MessageResponse>>

    @GET("/user/avatar")
    fun changeUserAvatar(
        @Query("file")
        filter: String,
        @Query("token")
        token: String
    ): Call<List<UserResponse>>
}