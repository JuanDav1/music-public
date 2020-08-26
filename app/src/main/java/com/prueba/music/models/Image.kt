package com.prueba.music.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Image(
    @SerializedName("#text")
    val text: String,
    @SerializedName("size")
    val size: String
): Serializable