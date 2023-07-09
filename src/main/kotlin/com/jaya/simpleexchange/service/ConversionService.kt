package com.jaya.simpleexchange.service

import com.jaya.simpleexchange.config.ExchangeApiProperties
import com.jaya.simpleexchange.dto.ConversionForm
import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.mapper.FormConversionMapper
import com.jaya.simpleexchange.repository.ConversionRepository
import com.jaya.simpleexchange.service.apiclient.ExchangeApi
import java.lang.RuntimeException
import java.math.BigDecimal
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ConversionService(
    private val repository: ConversionRepository,
    @Value("User not Found!")
    private val notFoundMessage: String
) {

    @Autowired
    lateinit var properties: ExchangeApiProperties

    fun create(conversionForm: ConversionForm): Conversion {
        val (amount, originalCurrency, destinyCurrency) = conversionForm
        val conversion: Conversion = conversionForm.toConversionEntity()

        val exchangeResult = ExchangeApi.getQuoteForCurrencies(
            originalCurrency = originalCurrency,
            destinyCurrency = destinyCurrency,
            properties = properties
        )

        conversion.rateConversion = calculateConversionRate(
            exchangeResult.rates[conversion.originalCurrency],
            exchangeResult.rates[conversion.destinyCurrency]
        )

        conversion.convertedAmount = calculateAmount(amount, conversion.rateConversion)

        return repository.save(conversion)
    }

    fun searchByUserId(userId: Long, pageable: Pageable): Page<Conversion>? {

        if (!repository.existsByUserId(userId)) {
            throw NotFoundException(notFoundMessage)
        }
        return repository.findAllByUserId(userId, pageable)
    }

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
