package com.app.exchanger.domain.rules

import com.app.exchanger.domain.model.ExchangeTransaction
import java.math.BigDecimal

class BaseExchangeFeeRule : ExchangeFeeRule {

    private val fee = BigDecimal.valueOf(0.007)

    override fun process(
        transaction: ExchangeTransaction,
    ): ExchangeFeeRuleResult {
        val fee = if (transaction.id > 5) {
            transaction.amount * fee
        } else {
            null
        }
        return ExchangeFeeRuleResult(fee)
    }
}
