package com.prueba.music.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Artist(
    @SerializedName("name")
    val name: String,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("image")
    val image: List<Image>
):Serializable