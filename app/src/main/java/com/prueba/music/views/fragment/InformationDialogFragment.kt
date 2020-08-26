package com.prueba.music.views.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.prueba.music.R
import com.prueba.music.models.Artist
import kotlinx.android.synthetic.main.fragment_dialog_information.view.*

private const val ARG_PARAM1 = "param1"

class InformationDialogFragment : DialogFragment() {

    private lateinit var artist: Artist


    override fun getTheme(): Int = R.style.CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            artist = it.getSerializable(ARG_PARAM1) as Artist

        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(700,700)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val view =  inflater.inflate(R.layout.fragment_dialog_information,container,false)
        view.name_artist.text = artist.name
        view.listeners_artist.text = artist.listeners
        view.mbid_artist.text = artist.mbid
        view.url_artist.text = artist.url
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(artist: Artist) =
            InformationDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, artist)
                }
            }
    }

}