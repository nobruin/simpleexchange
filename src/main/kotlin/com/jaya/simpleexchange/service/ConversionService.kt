package com.jaya.simpleexchange.service

import com.jaya.simpleexchange.config.ExchangeApiProperties
import com.jaya.simpleexchange.dto.ConversionForm
import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.mapper.FormConversionMapper
import com.jaya.simpleexchange.repository.ConversionRepository
import com.jaya.simpleexchange.repository.UserRepository
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
    private val userRepository: UserRepository,
    private val util: ConversionUtil,
    @Value("User not Found!")
    private val notFoundMessage: String,
    private val mapper: FormConversionMapper
) {

    @Autowired
    lateinit var properties: ExchangeApiProperties

    fun create(conversionForm: ConversionForm): Conversion {
        val (amount, originalCurrency, destinyCurrency, userId) = conversionForm

        if (!userRepository.existsById(userId)) {
            throw NotFoundException(notFoundMessage)
        }

        val conversion: Conversion = mapper.map(conversionForm)

        val exchangeResult = ExchangeApi.getQuoteForCurrencies(
            originalCurrency = originalCurrency,
            destinyCurrency = destinyCurrency,
            properties = properties
        )

        conversion.rateConversion = util.calculateConversionRate(
            exchangeResult.rates[conversion.originalCurrency],
            exchangeResult.rates[conversion.destinyCurrency]
        )

        conversion.convertedAmount = util.calculateAmount(amount, conversion.rateConversion)

        return repository.save(conversion)
    }

    fun searchByUserId(userId: Long, pageable: Pageable): Page<Conversion>? {

        if (!userRepository.existsById(userId)) {
            throw NotFoundException(notFoundMessage)
        }
        return repository.findAllByUserId(userId, pageable)
    }
}
