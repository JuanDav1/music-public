package com.prueba.music.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.prueba.music.R
import com.prueba.music.models.Artist
import com.prueba.music.models.ArtistModel
import com.prueba.music.utils.clickListener
import kotlinx.android.synthetic.main.item_artist.view.*
import java.util.stream.Collectors

class ArtistsAdapter(var listArtists: MutableList<ArtistModel>, val context: Context, val listener: clickListener): RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {
      class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.name_artist
        val image = view.image_artist
        val cardView = view.cardview_artist

        fun bind(artist: ArtistModel){
            name.text = artist.name
            var imageArtist = artist.image
            image.loadUrl(imageArtist)
        }

        private fun ImageView.loadUrl(url: String) {
            val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(factory))
                .into(this)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.item_artist,
                parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return listArtists.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listArtists[position]
        holder.bind(item)
        holder.cardView.setOnClickListener {
            listener.onClickListener(item)
        }
    }
}