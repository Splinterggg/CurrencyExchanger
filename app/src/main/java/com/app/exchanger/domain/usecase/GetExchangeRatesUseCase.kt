package com.app.exchanger.domain.usecase

import com.app.exchanger.data.source.model.Result
import com.app.exchanger.domain.model.ExchangeData
import com.app.exchanger.domain.repository.CurrencyExchangeRepository
import kotlinx.coroutines.flow.Flow

class GetExchangeRatesUseCase(
    private val repository: CurrencyExchangeRepository,
) {

    operator fun invoke(): Flow<Result<ExchangeData>> {
        return repository.getExchangeRates()
    }
}
