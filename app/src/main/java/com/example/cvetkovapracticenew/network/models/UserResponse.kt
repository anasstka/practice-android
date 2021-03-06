package com.example.cvetkovapracticenew.network.models

import com.google.gson.annotations.SerializedName

// класс для получения ответа get запроса получения пользователя
data class UserResponse(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String
)
