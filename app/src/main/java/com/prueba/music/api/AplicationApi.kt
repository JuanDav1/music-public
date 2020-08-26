package com.prueba.music.api

import com.prueba.music.models.Artists
import retrofit2.Response
import retrofit2.http.GET

interface AplicationApi {

    @GET("?method=geo.gettopartists&country=spain&api_key=829751643419a7128b7ada50de590067&format=json")
    suspend fun getArtists(): Response<Artists>

}