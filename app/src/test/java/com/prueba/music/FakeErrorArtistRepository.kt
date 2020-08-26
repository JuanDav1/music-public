package com.prueba.music

import com.prueba.music.api.OperationResult
import com.prueba.music.models.ArtistData
import com.prueba.music.models.Artists

class FakeErrorArtistRepository : ArtistData {

    private val mockException = Exception("Ocurrió un error")
    override suspend fun retrieveArtist(): OperationResult<Artists> {
        return OperationResult.Error(mockException)
    }


}