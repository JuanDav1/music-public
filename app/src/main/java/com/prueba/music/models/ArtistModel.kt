package com.prueba.music.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "ArtistModel")
data class ArtistModel (
    @PrimaryKey
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: String
):Serializable


