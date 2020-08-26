package com.prueba.music.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prueba.music.models.ArtistModel

@Database(
    entities = [ArtistModel::class],
    version = 1,
    exportSchema = false
)
abstract class AplicationDB : RoomDatabase() {
    abstract fun dataBaseDao(): DataBaseDao
}