package com.prueba.music.viewmodels

import androidx.lifecycle.ViewModel
import com.prueba.music.repositories.LocalRepository
import com.prueba.music.repositories.RemoteRepository

class ArtistsViewModel(private val remoteRepository: RemoteRepository, private val localReposiroty: LocalRepository):ViewModel() {


}