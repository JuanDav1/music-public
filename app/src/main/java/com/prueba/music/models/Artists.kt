package com.prueba.music.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Artists(
    @SerializedName("topartists")
    val topartists: Topartists
): Serializable