package com.henrimakela.mvvmaac.data.network

import com.henrimakela.mvvmaac.data.network.response.ChordResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.uberchord.com/v1/chords?nameLike=F

interface ApiService {

    @GET("chords")
    fun getChordsLike( @Query("nameLike") chord : String): Deferred<List<ChordResponse>>

    companion object {
        val BASE_URL = "https://api.uberchord.com/v1/"

        fun create( connectivityInterceptor: ConnectivityInterceptor): ApiService {
            val client = OkHttpClient.Builder().addInterceptor(connectivityInterceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BASE_URL)
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)

        }
    }
}