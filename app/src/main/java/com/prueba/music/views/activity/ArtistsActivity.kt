package com.prueba.music.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prueba.music.MusicApp
import com.prueba.music.R
import com.prueba.music.adapters.ArtistsAdapter
import com.prueba.music.models.Artist
import com.prueba.music.models.ArtistModel
import com.prueba.music.models.Artists
import com.prueba.music.utils.clickListener
import com.prueba.music.viewmodels.ArtistsViewModel
import com.prueba.music.viewmodels.ViewModelFactory
import com.prueba.music.views.fragment.InformationDialogFragment
import kotlinx.android.synthetic.main.activity_artists.*
import javax.inject.Inject

class ArtistsActivity : AppCompatActivity(), clickListener, SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    lateinit var adapter: ArtistsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ArtistsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MusicApp).getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArtistsViewModel::class.java)



        viewModel.isDataBaseNull()
        setUpViewModel()
        setUpSearch()


    }



    fun showtArtists(artists: List<ArtistModel>) {
        adapter = ArtistsAdapter(artists as MutableList<ArtistModel>, this, this)
        recycler_artist.adapter = adapter
        adapter.notifyDataSetChanged()
        text_error.visibility = View.GONE
    }

    override fun onClickListener(artist: ArtistModel) {
        val informationDialogFragmet = InformationDialogFragment.newInstance(artist)
        informationDialogFragmet.show(supportFragmentManager, "informationFragment")
    }

    fun setUpViewModel() {
        viewModel.responseLiveDataArtistModel.observe(
            this,
            Observer { artists -> showtArtists(artists) })
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)

    }


    private val isViewLoadingObserver = Observer<Boolean> {

        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility

    }


    private val onMessageErrorObserver = Observer<Any> {
        text_error.visibility = View.VISIBLE
        text_error.text = "Error $it"

    }

    private fun setUpSearch() {
        search_artist.setOnQueryTextListener(this)
        search_artist.setOnCloseListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        filterArtist(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        filterArtist(newText)
        return false
    }

    override fun onClose(): Boolean {
        return true
    }

    fun filterArtist(query: String?) {
        if (query != null && query.isNotEmpty()) {
            viewModel.filterArtist(query.trim())
        } else {
            viewModel.getArtist()
        }
    }


}