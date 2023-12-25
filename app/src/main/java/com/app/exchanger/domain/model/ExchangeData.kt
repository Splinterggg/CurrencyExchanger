package com.app.exchanger.domain.model

data class ExchangeData(
    val base: String,
    val date: String,
    val rates: List<ExchangeRate>,
)
