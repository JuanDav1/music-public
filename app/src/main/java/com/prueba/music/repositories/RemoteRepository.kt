package com.prueba.music.repositories

import com.prueba.music.api.AplicationApi
import com.prueba.music.api.OperationResult
import com.prueba.music.models.Artists
import com.prueba.music.models.ArtistData
import java.lang.Exception

class RemoteRepository(private var aplicationApiCall: AplicationApi):
    ArtistData {


    override suspend fun retrieveArtist(): OperationResult<Artists> {
        try {
            val response = aplicationApiCall.getArtists()
            response.let {
                return if (it.isSuccessful && it.body() != null){
                    val data = it.body()
                    OperationResult.Success(data)
                }else{
                    OperationResult.Error(Exception("Ocurrio un error"))
                }
            }

        }catch (e: Exception){
            return OperationResult.Error(e)
        }

    }
}
