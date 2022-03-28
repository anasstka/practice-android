package com.example.app_wear.network.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    var token: Int
)