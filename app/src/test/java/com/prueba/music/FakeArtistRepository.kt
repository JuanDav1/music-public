package com.prueba.music

import com.prueba.music.api.AplicationApi
import com.prueba.music.api.OperationResult
import com.prueba.music.models.*

class FakeArtistRepository():ArtistData {

    lateinit var  mockObject: Artists
    lateinit var  topArtist: Topartists
    val artist: MutableList<Artist> = mutableListOf()
    val atrr: Attr = Attr("spain","2","10","100","1000")
    private var images: MutableList<Image> = mutableListOf()

    init {
        mockData()
    }

    fun mockData(){
        images.add(Image("image","large"))
        artist.add(Artist("artist1","1000","123-1212","www.","none",images))
        topArtist= Topartists(artist,atrr)
        mockObject = Artists(topArtist)
    }

    override suspend fun retrieveArtist(): OperationResult<Artists> {
        return OperationResult.Success(mockObject)
    }


}