package com.app.exchanger.domain.rules

import com.app.exchanger.domain.model.ExchangeTransaction

interface ExchangeFeeRule {

    fun process(
        transaction: ExchangeTransaction
    ): ExchangeFeeRuleResult
}