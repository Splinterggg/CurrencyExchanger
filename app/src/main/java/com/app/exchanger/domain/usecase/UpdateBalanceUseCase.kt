package com.app.exchanger.domain.usecase

import com.app.exchanger.domain.model.Balance
import com.app.exchanger.domain.model.ExchangeTransactionResult

class UpdateBalanceUseCase {

    operator fun invoke(
        balance: List<Balance>,
        transactionResult: ExchangeTransactionResult,
    ): List<Balance> {
        val balanceChange = transactionResult.change
        return if (balance.firstOrNull { it.currency == balanceChange.buyCurrency } == null) {
            balance.map {
                when (it.currency) {
                    balanceChange.sellCurrency -> {
                        it.copy(amount = it.amount + balanceChange.sellBalanceChange)
                    }

                    else -> {
                        it
                    }
                }
            } + Balance(
                currency = balanceChange.buyCurrency,
                amount = balanceChange.buyBalanceChange,
            )
        } else {
            balance.map {
                when (it.currency) {
                    balanceChange.buyCurrency -> {
                        it.copy(amount = it.amount + balanceChange.buyBalanceChange)
                    }

                    balanceChange.sellCurrency -> {
                        it.copy(amount = it.amount + balanceChange.sellBalanceChange)
                    }

                    else -> {
                        it
                    }
                }
            }
        }
    }
}