package com.example.cvetkovapracticenew.network.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("movieId")
    var movieId: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("age")
    var age: String,
    @SerializedName("poster")
    var posterUrl: String,
)
