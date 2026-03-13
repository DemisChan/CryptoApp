package com.example.cryptoapp.di

import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {
        @Provides
        fun provideCoinInfoDao(appDatabase: AppDatabase): CoinInfoDao {
            return appDatabase.coinPriceInfoDao()
        }

    }

}