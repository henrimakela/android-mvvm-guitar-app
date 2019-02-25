package com.henrimakela.mvvmaac.ui.chords

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.Observer

import com.henrimakela.mvvmaac.R
import com.henrimakela.mvvmaac.data.network.ApiService
import com.henrimakela.mvvmaac.data.network.ConnectivityInterceptorImpl
import com.henrimakela.mvvmaac.data.network.MusicTheoryDataSourceImpl
import com.henrimakela.mvvmaac.data.repository.MusicTheoryObject
import com.henrimakela.mvvmaac.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.chords_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ChordsFragment : ScopedFragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModelFactory: ChordsViewModelFactory by instance()

    private lateinit var viewModel: ChordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chords_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ChordsViewModel::class.java)

        bindUI()
    }

    private fun bindUI(){

        /*val chords = viewModel.chords.await()
        chords.observe(this@ChordsFragment, Observer {
            if(it == null)return@Observer
            println(it.toString())
        })*/

        viewModel.currentKey.observe(this@ChordsFragment, Observer {
            println("outside coroutine")

            launch {
                println("Inside Coroutine")
                group_loading.visibility = View.VISIBLE
                viewModel.getChordsLike(it).observe(this@ChordsFragment, Observer {
                    data_text.text = ""
                    if(it.err != null){
                        data_text.text = it.err
                    }
                    else{
                        for (chordResponse in it.response!!) {
                            data_text.append(chordResponse.chordName + "\n")
                        }
                    }

                    group_loading.visibility = View.GONE
                })
            }
        })

        key_picker.minValue = 0
        key_picker.maxValue = MusicTheoryObject.MAJOR_KEYS.size - 1
        key_picker.displayedValues = MusicTheoryObject.MAJOR_KEYS

        key_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.currentKey.postValue(MusicTheoryObject.MAJOR_KEYS[newVal])
        }


    }

}
