package com.jaya.simpleexchange.service

import com.jaya.simpleexchange.config.ExchangeApiProperties
import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.repository.ConversionRepository
import com.jaya.simpleexchange.service.apiclient.ExchangeApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConversionService(
    private val repository: ConversionRepository
) {

    @Autowired
    lateinit var properties: ExchangeApiProperties

    fun create(conversion: Conversion): Conversion {

        val exchangeResult = ExchangeApi.getQuoteForCurrencies(
            originalCurrency = conversion.originalCurrency,
            destinyCurrency = conversion.destinyCurrency,
            properties = properties
        )

        conversion.rateConversion = (
                exchangeResult.rates[conversion.destinyCurrency]?.
                    div(exchangeResult.rates[conversion.originalCurrency]!!)
                )

        conversion.convertedAmount = conversion.amount?.let { conversion.rateConversion?.times(it) }!!

        return repository.save(conversion)
    }
}
