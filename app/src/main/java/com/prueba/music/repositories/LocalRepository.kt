package com.prueba.music.repositories

import com.prueba.music.database.DataBaseDao
import com.prueba.music.models.ArtistModel

class LocalRepository(private var dataBaseDao: DataBaseDao) {

    suspend fun insertArtist(artistModel: List<ArtistModel>){
        dataBaseDao.insertArtist(artistModel)
    }

    suspend fun getArtist():List<ArtistModel>{
        return dataBaseDao.getArtist()
    }

    suspend fun numberRecordsArtist():Int{
        return dataBaseDao.numberRecordsArtist()
    }

    suspend fun filterArtist(name:String):List<ArtistModel>{
        return dataBaseDao.filterArtist(name)
    }


}