package com.app.exchanger.domain.model

import java.math.BigDecimal

data class ExchangeRate(
    val currency: String,
    val rate: BigDecimal,
)