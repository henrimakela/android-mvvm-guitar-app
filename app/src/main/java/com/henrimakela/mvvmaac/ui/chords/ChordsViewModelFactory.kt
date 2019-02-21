package com.henrimakela.mvvmaac.ui.chords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.henrimakela.mvvmaac.data.repository.MusicTheoryRepository


class ChordsViewModelFactory(private val musicTheoryRepository: MusicTheoryRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChordsViewModel(musicTheoryRepository) as T
    }

}