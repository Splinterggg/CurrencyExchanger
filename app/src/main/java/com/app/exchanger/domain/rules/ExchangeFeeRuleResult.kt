package com.app.exchanger.domain.rules

import java.math.BigDecimal

data class ExchangeFeeRuleResult(
    val fee: BigDecimal? = null,
)
