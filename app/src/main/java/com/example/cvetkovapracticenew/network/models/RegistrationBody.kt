package com.example.cvetkovapracticenew.network.models

import com.google.gson.annotations.SerializedName

// класс для отправки тела post запроса при регшистрации
data class RegistrationBody(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String
)
