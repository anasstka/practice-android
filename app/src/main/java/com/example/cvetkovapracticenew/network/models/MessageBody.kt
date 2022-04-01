package com.example.cvetkovapracticenew.network.models

import com.google.gson.annotations.SerializedName

// класс для отправки тела post запроса добавления нового сообщения в чат
data class MessageBody(
    @SerializedName("text")
    var text: String,
)
