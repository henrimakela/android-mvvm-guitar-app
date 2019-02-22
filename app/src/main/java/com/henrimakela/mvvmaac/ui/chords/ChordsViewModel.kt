package com.henrimakela.mvvmaac.ui.chords

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.henrimakela.mvvmaac.data.network.response.ChordResponse
import com.henrimakela.mvvmaac.data.repository.MusicTheoryRepository
import com.henrimakela.mvvmaac.internal.lazyDeferred

class ChordsViewModel(private val musicTheoryRepository: MusicTheoryRepository) : ViewModel() {
    val chords by lazyDeferred {
        musicTheoryRepository.getChordsLike("F")
    }
    val currentKey: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    suspend fun getChordsLike(chord: String): LiveData<List<ChordResponse>>{
        return musicTheoryRepository.getChordsLike(chord)
    }
}
