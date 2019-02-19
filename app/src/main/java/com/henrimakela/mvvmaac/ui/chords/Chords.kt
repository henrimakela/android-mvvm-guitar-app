package com.henrimakela.mvvmaac.ui.chords

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.henrimakela.mvvmaac.R

class Chords : Fragment() {

    companion object {
        fun newInstance() = Chords()
    }

    private lateinit var viewModel: ChordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chords_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChordsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
