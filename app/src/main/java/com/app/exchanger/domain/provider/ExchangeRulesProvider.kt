package com.app.exchanger.domain.provider

import com.app.exchanger.domain.rules.BaseExchangeFeeRule
import com.app.exchanger.domain.rules.ExchangeFeeRule

class ExchangeRulesProvider {

    val rules = listOf<ExchangeFeeRule>(
        BaseExchangeFeeRule(),
    )
}