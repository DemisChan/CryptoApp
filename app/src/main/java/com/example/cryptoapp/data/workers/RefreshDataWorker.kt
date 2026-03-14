package com.example.cryptoapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkerParameters
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiService
import com.example.cryptoapp.di.ChildWorkerFactory
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService
) : CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }

    companion object {
        const val WORKER_NAME = "RefreshDataWorker"
        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequest.Builder(RefreshDataWorker::class.java).build()
        }
    }
}

class Factory @Inject constructor(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : ChildWorkerFactory {
    override fun create(
        context: Context,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return RefreshDataWorker(
            context,
            workerParameters,
            mapper,
            coinInfoDao,
            apiService
        )
    }
}
