package com.prueba.music.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prueba.music.models.ArtistModel

@Dao
interface DataBaseDao {
    @Insert
    suspend fun insertArtist(artistModel: List<ArtistModel>)

    @Query("SELECT * FROM ArtistModel")
    suspend fun getArtist():List<ArtistModel>

    @Query("SELECT COUNT(*) FROM ArtistModel")
    suspend fun numberRecordsArtist():Int

    @Query("SELECT * FROM ArtistModel WHERE name LIKE '%' || :name ||'%'")
    suspend fun filterArtist(name:String):List<ArtistModel>

}