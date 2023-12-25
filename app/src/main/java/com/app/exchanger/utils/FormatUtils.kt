package com.app.exchanger.utils

import java.math.BigDecimal
import java.math.RoundingMode

fun formatDecimal(decimal: BigDecimal): String =
    decimal.setScale(2, RoundingMode.HALF_EVEN).toPlainString()