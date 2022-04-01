package com.example.cvetkovapracticenew.network.models

import com.google.gson.annotations.SerializedName

// класс для отправки тела post запроса при авторизации
data class LoginBody(
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String
)