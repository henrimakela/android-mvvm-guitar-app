package com.henrimakela.mvvmaac.ui.cof

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.henrimakela.mvvmaac.R

class CofFragment : Fragment() {

    companion object {
        fun newInstance() = CofFragment()
    }

    private lateinit var viewModel: CofViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cof_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CofViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
