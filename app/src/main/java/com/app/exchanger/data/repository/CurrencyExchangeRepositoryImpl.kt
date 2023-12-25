package com.app.exchanger.data.repository

import com.app.exchanger.data.source.CurrencyExchangeApi
import com.app.exchanger.data.source.model.Result
import com.app.exchanger.domain.model.ExchangeData
import com.app.exchanger.domain.repository.CurrencyExchangeRepository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import java.util.concurrent.TimeUnit

private val SYNC_PERIOD = TimeUnit.SECONDS.toMillis(5)

class CurrencyExchangeRepositoryImpl(
    private val apiService: CurrencyExchangeApi,
) : CurrencyExchangeRepository {

    override fun getExchangeRates(): Flow<Result<ExchangeData>> = flow {
        while (currentCoroutineContext().isActive) {
            emit(Result.Loading())
            try {
                val response = apiService.getExchangeRates()
                emit(
                    Result.Success(
                        ExchangeData(
                            base = response.base!!,
                            date = response.date!!,
                            rates = response.getExchangeRates(),
                        ),
                    ),
                )
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e))
            }
            delay(SYNC_PERIOD)
        }
    }
}
