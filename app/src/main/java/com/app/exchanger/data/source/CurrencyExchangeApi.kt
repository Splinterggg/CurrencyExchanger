package com.app.exchanger.data.source

import com.app.exchanger.data.source.model.ExchangeRateResponse
import retrofit2.http.GET

interface CurrencyExchangeApi {

    @GET("currency-exchange-rates")
    suspend fun getExchangeRates(): ExchangeRateResponse

    companion object {
        const val BASE_URL: String = "https://developers.paysera.com/tasks/api/"
    }
}