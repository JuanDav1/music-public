package com.prueba.music.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prueba.music.MusicApp
import com.prueba.music.R
import com.prueba.music.adapters.ArtistsAdapter
import com.prueba.music.models.Artist
import com.prueba.music.models.Artists
import com.prueba.music.utils.clickListener
import com.prueba.music.viewmodels.ArtistsViewModel
import com.prueba.music.viewmodels.ViewModelFactory
import com.prueba.music.views.fragment.InformationDialogFragment
import kotlinx.android.synthetic.main.activity_artists.*
import javax.inject.Inject

class ArtistsActivity : AppCompatActivity(),clickListener {

    lateinit var adapter: ArtistsAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ArtistsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MusicApp).getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArtistsViewModel::class.java)

        viewModel.responseLiveDataArtists.observe(this,Observer{artists -> showArtists(artists)})
        viewModel.loadArtist()
    }


    fun showArtists(artists: Artists){
        adapter = ArtistsAdapter(artists.topartists.artist,this,this)
        recycler_artist.adapter = adapter
        }

    override fun onClickListener(artist: Artist) {
        val informationDialogFragmet = InformationDialogFragment.newInstance(artist)
        informationDialogFragmet.show(supportFragmentManager,"informationFragment")
            }

}