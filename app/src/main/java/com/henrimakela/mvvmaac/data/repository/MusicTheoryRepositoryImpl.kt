package com.henrimakela.mvvmaac.data.repository

import androidx.lifecycle.LiveData
import com.henrimakela.mvvmaac.data.network.MusicTheoryDataSource
import com.henrimakela.mvvmaac.data.network.response.ChordResponse
import com.henrimakela.mvvmaac.data.network.response.DataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicTheoryRepositoryImpl(private val musicTheoryDataSource: MusicTheoryDataSource) : MusicTheoryRepository {

    override suspend fun getChordsLike(chord: String): LiveData<DataWrapper> {
        println("MusicTheoryRepoImpl")
        musicTheoryDataSource.fetchChords(chord)
        return musicTheoryDataSource.downloadedChords
    }

}