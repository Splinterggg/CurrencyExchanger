package com.app.exchanger.data.source.model

import com.app.exchanger.domain.model.ExchangeRate
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ExchangeRateResponse(
    @SerializedName("base")
    var base: String? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("rates")
    var rates: Map<String, Double>? = emptyMap(),
) {

    fun getExchangeRates(): List<ExchangeRate> {
        return rates?.map {
            ExchangeRate(
                currency = it.key,
                rate = BigDecimal.valueOf(it.value),
            )
        } ?: emptyList()
    }
}
