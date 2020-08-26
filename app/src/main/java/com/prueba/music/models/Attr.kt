package com.prueba.music.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Attr(
    @SerializedName("country")
    val country: String,
    @SerializedName("page")
    val page: String,
    @SerializedName("perPage")
    val perPage: String,
    @SerializedName("totalPages")
    val totalPages: String,
    @SerializedName("total")
    val total: String
): Serializable