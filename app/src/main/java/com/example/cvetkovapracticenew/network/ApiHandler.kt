package com.example.cvetkovapracticenew.network

import com.example.cvetkovapracticenew.data.userToken
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHandler {
    private val retrofit: Retrofit

    // получение класса на основе ApiService, через который будет происходить
    // вызов запроосов к api
    val service: ApiService
        get() = retrofit.create(ApiService::class.java)

    companion object {
        private var mInstance: ApiHandler? = null

        private const val BASE_URL = "http://cinema.areas.su/"
        // получение экземпляра класса ApiHandler
        val instance: ApiHandler
            get() {
                if (mInstance == null) {
                    mInstance = ApiHandler()
                }
                return mInstance as ApiHandler
            }
    }

    init {
        // настройка логов для запроосов
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    // установка header параметра авторизации типа Bearer
                    .addHeader("Authorization", "Bearer $userToken").build()
                chain.proceed(request)
            })

        // настройка retrofit для работы
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}