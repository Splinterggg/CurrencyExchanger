package com.app.exchanger.ui

import com.app.exchanger.domain.model.Balance
import com.app.exchanger.domain.model.ExchangeRate
import com.app.exchanger.domain.model.ExchangeTransactionResult

const val BASE_CURRENCY = "EUR"

data class ExchangeCurrencyState(
    val balance: List<Balance> = emptyList(),
    val availableCurrencies: List<String> = emptyList(),
    val allCurrencies: List<String> = emptyList(),
    val exchangeRates: List<ExchangeRate> = emptyList(),
    val receiveAmount: String = "0.00",
    val message: String? = null,
    val transactionResult: ExchangeTransactionResult? = null,
)

sealed class UIEvent {
    data class Exchange(
        val sellCurrency: String,
        val sellAmount: String,
        val buyCurrency: String,
    ) : UIEvent()

    data class GetExchangeRate(
        val sellCurrency: String,
        val sellAmount: String,
        val buyCurrency: String,
    ) : UIEvent()

    object CloseResultDialog : UIEvent()
}

sealed class Action {
    object ClearInputField : Action()
}
