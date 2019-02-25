package com.henrimakela.mvvmaac.data.network


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.henrimakela.mvvmaac.data.network.response.DataWrapper
import com.henrimakela.mvvmaac.internal.NoConnectivityException
import java.io.IOException
import java.net.SocketTimeoutException

class MusicTheoryDataSourceImpl(private val apiService: ApiService) : MusicTheoryDataSource {

    private val _downloadedChords = MutableLiveData<DataWrapper>()

    //downloadedChords enkapsuloidaan, jotta sitä ei voida muuttaa muualta kuin täältä
    override val downloadedChords: LiveData<DataWrapper>
        get() = _downloadedChords

    override suspend fun fetchChords(chord: String) {
        try {
            println("Fetch chords data source impl")
            val fetchChords = apiService.getChordsLike(chord).await()
            val responseWrapper = DataWrapper(null, fetchChords)
            //val fakeResponse = listOf(ChordResponse("F", "0", "", "", "", ""))

            _downloadedChords.postValue(responseWrapper)

        }
        catch (e: IOException){
            when(e){
                is NoConnectivityException -> _downloadedChords.postValue(DataWrapper("No internet connection", null))
                is SocketTimeoutException -> _downloadedChords.postValue(DataWrapper("Server timed out", null))
            }

        }
    }
}