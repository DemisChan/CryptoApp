package com.example.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.workers.RefreshWorkerDataFactory
import com.example.cryptoapp.di.DaggerAppComponent


class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(
                RefreshWorkerDataFactory(
                    CoinMapper(),
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                )
            ).build()
}
