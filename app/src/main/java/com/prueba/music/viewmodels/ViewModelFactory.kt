package com.prueba.music.viewmodels

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prueba.music.repositories.LocalRepository
import com.prueba.music.repositories.RemoteRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val remoteRepository: RemoteRepository, private var localReposiroty: LocalRepository): ViewModelProvider.Factory  {

    @NonNull
    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
        modelClass.isAssignableFrom(ArtistsViewModel::class.java)
        return ArtistsViewModel(remoteRepository,localReposiroty) as T

    }
}