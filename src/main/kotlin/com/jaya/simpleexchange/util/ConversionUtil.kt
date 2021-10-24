package com.jaya.simpleexchange.util


import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class ConversionUtil {

    fun calculateConversionRate(currency: Double?, destinyCurrency: Double?): BigDecimal? {
        return (destinyCurrency?.div(currency!!))?.toBigDecimal()
    }

    fun calculateAmount(amount: BigDecimal, rateConversion: BigDecimal?): BigDecimal {
        return amount.let { rateConversion?.times(it) }!!
    }
}