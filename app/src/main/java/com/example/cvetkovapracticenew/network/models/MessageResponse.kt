package com.example.cvetkovapracticenew.network.models

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("chatId")
    var chatId: String,
    @SerializedName("messageId")
    var messageId: String,
    @SerializedName("creationDateTime")
    var creationDateTime: String,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("avatar")
    var userAvatar: String,
    @SerializedName("text")
    var text: String,
)
