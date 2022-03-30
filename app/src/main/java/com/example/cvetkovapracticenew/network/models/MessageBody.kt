package com.example.cvetkovapracticenew.network.models

import com.google.gson.annotations.SerializedName

data class MessageBody(
    @SerializedName("text")
    var text: String,
)
