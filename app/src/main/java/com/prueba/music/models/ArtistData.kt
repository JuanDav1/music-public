package com.prueba.music.models

import com.prueba.music.api.OperationResult
import com.prueba.music.models.Artists

interface ArtistData {

    suspend fun retrieveArtist():OperationResult<Artists>

}