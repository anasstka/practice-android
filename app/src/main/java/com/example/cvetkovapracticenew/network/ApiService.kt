package com.example.cvetkovapracticenew.network

import com.example.cvetkovapracticenew.network.models.LoginBody
import com.example.cvetkovapracticenew.network.models.LoginResponse
import com.example.cvetkovapracticenew.network.models.RegistrationBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/register")
    fun register(@Body registrationBody: RegistrationBody): Call<ResponseBody>

    @POST("/auth/login")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>
}