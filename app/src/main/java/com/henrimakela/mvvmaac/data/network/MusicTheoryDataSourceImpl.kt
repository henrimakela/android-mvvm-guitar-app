package com.henrimakela.mvvmaac.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.henrimakela.mvvmaac.data.network.response.ChordResponse
import com.henrimakela.mvvmaac.internal.NoConnectivityException
import java.io.IOException
import java.net.SocketTimeoutException

class MusicTheoryDataSourceImpl(private val apiService: ApiService) : MusicTheoryDataSource {

    private val _downloadedChords = MutableLiveData<List<ChordResponse>>()

    //downloadedChords enkapsuloidaan, jotta sitä ei voida muuttaa muualta kuin täältä
    override val downloadedChords: LiveData<List<ChordResponse>>
        get() = _downloadedChords

    override suspend fun fetchChords(chord: String) {
        try {
            println("Fetch chords data source impl")
            val fetchChords = apiService.getChordsLike(chord).await()

            //val fakeResponse = listOf(ChordResponse("F", "0", "", "", "", ""))
            _downloadedChords.postValue(fetchChords)
        }
        catch (e: IOException){
            when(e){
                is NoConnectivityException -> Log.e("Connectivity", "No internet connection", e)
                is SocketTimeoutException -> Log.e("Timeout", "Server timeout")
            }

        }
    }
}