package com.example.app_wear.network

import com.example.app_wear.data.userToken
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHandler {
    private val retrofit: Retrofit

    val service: ApiService
        get() = retrofit.create(ApiService::class.java)

    companion object {
        private var mInstance: ApiHandler? = null

        private const val BASE_URL = "http://cinema.areas.su/"
        val instance: ApiHandler
            get() {
                if (mInstance == null) {
                    mInstance = ApiHandler()
                }
                return mInstance as ApiHandler
            }
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $userToken").build()
                    return chain.proceed(request)
                }
            })

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}