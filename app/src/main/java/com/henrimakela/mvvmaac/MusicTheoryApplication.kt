package com.henrimakela.mvvmaac

import android.app.Application
import com.henrimakela.mvvmaac.data.network.*
import com.henrimakela.mvvmaac.data.repository.MusicTheoryRepository
import com.henrimakela.mvvmaac.data.repository.MusicTheoryRepositoryImpl
import com.henrimakela.mvvmaac.ui.chords.ChordsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MusicTheoryApplication() : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MusicTheoryApplication))
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiService.create(instance()) }
        bind<MusicTheoryDataSource>() with singleton { MusicTheoryDataSourceImpl(instance()) }
        bind<MusicTheoryRepository>() with singleton { MusicTheoryRepositoryImpl(instance()) }
        bind() from provider { ChordsViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()

    }

}