package com.prueba.music.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Topartists(
    @SerializedName("artist")
    val artist: List<Artist>,
    @SerializedName("@attr")
    val attr: Attr
): Serializable