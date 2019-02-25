package com.henrimakela.mvvmaac.data.repository

import androidx.lifecycle.LiveData
import com.henrimakela.mvvmaac.data.network.response.ChordResponse
import com.henrimakela.mvvmaac.data.network.response.DataWrapper

interface MusicTheoryRepository {
    suspend fun getChordsLike(chord: String): LiveData<DataWrapper>
}