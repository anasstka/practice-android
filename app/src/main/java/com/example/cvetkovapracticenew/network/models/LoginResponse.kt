package com.example.cvetkovapracticenew.network.models

import com.google.gson.annotations.SerializedName

// класс для получения ответа post запроса при авторизации
data class LoginResponse(
    @SerializedName("token")
    var token: Int
)