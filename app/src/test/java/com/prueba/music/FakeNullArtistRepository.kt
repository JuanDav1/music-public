package com.prueba.music

import com.prueba.music.api.OperationResult
import com.prueba.music.models.ArtistData
import com.prueba.music.models.Artists

class FakeNullArtistRepository: ArtistData {
    override suspend fun retrieveArtist(): OperationResult<Artists> {
    return OperationResult.Success(null)
    }



}