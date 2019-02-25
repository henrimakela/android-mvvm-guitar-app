package com.henrimakela.mvvmaac.data.network

import androidx.lifecycle.LiveData
import com.henrimakela.mvvmaac.data.network.response.ChordResponse
import com.henrimakela.mvvmaac.data.network.response.DataWrapper

interface MusicTheoryDataSource {
    val downloadedChords: LiveData<DataWrapper>

    suspend fun fetchChords(chord: String)
}