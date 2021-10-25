package com.jaya.simpleexchange.util


import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.math.BigDecimal

@Component
class ConversionUtil {

    fun calculateConversionRate(currency: Double?, destinyCurrency: Double?): BigDecimal? {

        when{
            currency == null || currency <= 0.0 -> throw RuntimeException("values or Values is invalid for this operation")
            destinyCurrency == null || destinyCurrency <= 0.0 -> throw RuntimeException("values or Values is invalid for this operation")
        }

        return (destinyCurrency?.div(currency!!))?.toBigDecimal()
    }

    fun calculateAmount(amount: BigDecimal, rateConversion: BigDecimal?): BigDecimal {

        if(rateConversion == null || rateConversion <= "0.0".toBigDecimal()){
            throw RuntimeException("value is invalid for this operation")
        }
        return amount.let { rateConversion.times(it) }
    }
}