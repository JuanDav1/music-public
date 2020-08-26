package com.prueba.music.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.music.api.OperationResult
import com.prueba.music.models.Artists
import com.prueba.music.repositories.LocalRepository
import com.prueba.music.repositories.RemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistsViewModel(private val remoteRepository: RemoteRepository?, private val localReposiroty: LocalRepository?):ViewModel() {

    private val _responseLiveDataArtists = MutableLiveData<Artists>()
    val responseLiveDataArtists : LiveData<Artists> = _responseLiveDataArtists

    private val _isEmpty=MutableLiveData<Boolean>()
    val isEmpty:LiveData<Boolean> = _isEmpty

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    fun loadArtist(){
        viewModelScope.launch {

           var result: OperationResult<Artists> = withContext(Dispatchers.IO){ remoteRepository!!.retrieveArtist() }
            when(result){
                is OperationResult.Success ->{

                    if(result.data == null){
                        _isEmpty.postValue(true)
                    }else{
                        _responseLiveDataArtists.value = result.data
                    }

                }
                is OperationResult.Error -> {
                    Log.w("api",result.exception)
                    _onMessageError.postValue(result.exception)
                }


            }



        }
    }

}