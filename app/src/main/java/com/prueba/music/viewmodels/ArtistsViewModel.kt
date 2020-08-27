package com.prueba.music.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.music.api.OperationResult
import com.prueba.music.models.ArtistModel
import com.prueba.music.models.Artists
import com.prueba.music.repositories.LocalRepository
import com.prueba.music.repositories.RemoteRepository
import kotlinx.coroutines.*

class ArtistsViewModel(
    private val remoteRepository: RemoteRepository?,
    private val localReposiroty: LocalRepository?
) : ViewModel() {

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _responseLiveDataArtists = MutableLiveData<Artists>()
    val responseLiveDataArtists: LiveData<Artists> = _responseLiveDataArtists

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _responseLiveDataGetArtistModel = MutableLiveData<List<ArtistModel>>()
    val responseLiveDataArtistModel = _responseLiveDataGetArtistModel

    var listArtistModel:MutableList<ArtistModel> = mutableListOf()

    fun loadArtist() {

        viewModelScope.launch {
            _isViewLoading.postValue(true)
            var result: OperationResult<Artists> =
                withContext(Dispatchers.IO) { remoteRepository!!.retrieveArtist() }
            when (result) {
                is OperationResult.Success -> {
                    if (result.data == null) {
                        _isEmpty.postValue(true)
                    } else {
                        _responseLiveDataArtists.value = result.data

                        result.data!!.topartists.artist.forEach {
                            listArtistModel.add(ArtistModel(it.name,it.listeners,it.mbid,it.url,it.streamable,it.image[2].text))
                        }
                        insertArtist(listArtistModel)
                    }
                }
                is OperationResult.Error -> {
                    Log.w("api", result.exception)
                    _onMessageError.postValue(result.exception)
                }

            }
            _isViewLoading.postValue(false)
        }
    }


    fun isDataBaseNull(){

        viewModelScope.launch(Dispatchers.IO) {
    if( localReposiroty!!.numberRecordsArtist() == 0){
        loadArtist()
    }else{
        getArtist()
    }


        }
    }

    fun getArtist(){


        viewModelScope.launch {
            _responseLiveDataGetArtistModel.postValue(localReposiroty!!.getArtist())
        }

    }

    fun insertArtist(artistModel: MutableList<ArtistModel>){
        viewModelScope.launch {
            localReposiroty!!.insertArtist(artistModel)
            getArtist()
        }
    }


}