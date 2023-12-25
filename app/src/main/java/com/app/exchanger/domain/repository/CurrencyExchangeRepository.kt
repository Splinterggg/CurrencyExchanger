package com.app.exchanger.domain.repository

import com.app.exchanger.data.source.model.Result
import com.app.exchanger.domain.model.ExchangeData
import kotlinx.coroutines.flow.Flow

interface CurrencyExchangeRepository {

    fun getExchangeRates(): Flow<Result<ExchangeData>>
}
