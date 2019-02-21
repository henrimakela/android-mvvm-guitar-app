package com.henrimakela.mvvmaac.data.repository

import androidx.lifecycle.LiveData
import com.henrimakela.mvvmaac.data.network.MusicTheoryDataSource
import com.henrimakela.mvvmaac.data.network.response.ChordResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicTheoryRepositoryImpl(private val musicTheoryDataSource: MusicTheoryDataSource) : MusicTheoryRepository {

    init {
        musicTheoryDataSource.downloadedChords.observeForever{newValues ->
            //CACHE TO LOCAL DB
            println("MusicTheoryRepositoryImpl: $newValues")
        }
    }
    override suspend fun getChordsLike(chord: String): LiveData<List<ChordResponse>> {
        println("MusicTheoryRepoImpl")
        musicTheoryDataSource.fetchChords(chord)
        return musicTheoryDataSource.downloadedChords
    }

    private suspend fun initChordData(){

    }
    private suspend fun fetchChordLike(){

    }
}