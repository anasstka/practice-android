package com.example.cvetkovapracticenew.network

import com.example.cvetkovapracticenew.network.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

// интерфейс для отправки запросов к api через retrofit
interface ApiService {

    // отправка post запроса для регистрации
    @POST("/auth/register")
    fun register(
        // тело запроса
        @Body
        registrationBody: RegistrationBody
    ): Call<ResponseBody> // ответ запроса

    @POST("/auth/login")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>

    // отправка get запроса для получения списка фильмов
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

    @POST("/chats/{chatId}/messages")
    fun sendMessage(
        @Path("chatId")
        chatId: Int,
        @Body
        messageBody: MessageBody
    ): Call<MessageResponse>

    @GET("/user/avatar")
    fun changeUserAvatar(
        @Query("file")
        filter: String,
        @Query("token")
        token: String
    ): Call<List<UserResponse>>
}