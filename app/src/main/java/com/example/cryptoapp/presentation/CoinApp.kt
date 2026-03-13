package com.example.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.data.workers.RefreshWorkerDataFactory
import com.example.cryptoapp.di.DaggerAppComponent
import javax.inject.Inject


class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: RefreshWorkerDataFactory


    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }


    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(
                workerFactory
            ).build()
}
