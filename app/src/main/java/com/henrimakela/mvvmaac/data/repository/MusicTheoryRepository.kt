package com.henrimakela.mvvmaac.data.repository

import androidx.lifecycle.LiveData
import com.henrimakela.mvvmaac.data.network.response.ChordResponse

interface MusicTheoryRepository {
    suspend fun getChordsLike(chord: String): LiveData<List<ChordResponse>>
}