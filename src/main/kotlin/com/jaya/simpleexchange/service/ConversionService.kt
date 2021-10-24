package com.jaya.simpleexchange.service

import com.jaya.simpleexchange.config.ExchangeApiProperties
import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.repository.ConversionRepository
import com.jaya.simpleexchange.service.apiclient.ExchangeApi
import com.jaya.simpleexchange.util.ConversionUtil
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ConversionService(
    private val repository: ConversionRepository,
    private val util: ConversionUtil,
    @Value("User not Found!")
    private val notFoundMessage: String
) {

    @Autowired
    lateinit var properties: ExchangeApiProperties

    fun create(conversion: Conversion): Conversion {
        val (id, userId, amount, originalCurrency, destinyCurrency) = conversion

        val exchangeResult = ExchangeApi.getQuoteForCurrencies(
            originalCurrency = originalCurrency,
            destinyCurrency = destinyCurrency,
            properties = properties
        )

        conversion.rateConversion = util.calculateConversionRate(
            exchangeResult.rates[conversion.destinyCurrency],
            exchangeResult.rates[conversion.originalCurrency]
        )

        conversion.convertedAmount = util.calculateAmount(amount, conversion.rateConversion)

        return repository.save(conversion)
    }

    fun searchByUserId(userId: Long, pageable: Pageable): Page<Conversion>?  {

        val conversionsPage = repository.findByUserId(userId, pageable)

        if(conversionsPage?.isEmpty == true){
            throw NotFoundException(notFoundMessage)
        }

        return conversionsPage
    }
}
