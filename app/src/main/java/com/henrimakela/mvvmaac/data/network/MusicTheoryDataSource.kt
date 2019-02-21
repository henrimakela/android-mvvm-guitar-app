package com.henrimakela.mvvmaac.data.network

import androidx.lifecycle.LiveData
import com.henrimakela.mvvmaac.data.network.response.ChordResponse

interface MusicTheoryDataSource {
    val downloadedChords: LiveData<List<ChordResponse>>

    suspend fun fetchChords(chord: String)
}