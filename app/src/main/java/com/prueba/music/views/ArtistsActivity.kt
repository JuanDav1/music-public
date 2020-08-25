package com.prueba.music.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.prueba.music.MusicApp
import com.prueba.music.R
import com.prueba.music.viewmodels.ArtistsViewModel
import com.prueba.music.viewmodels.ViewModelFactory
import javax.inject.Inject

class ArtistsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ArtistsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MusicApp).getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArtistsViewModel::class.java)
    }
}