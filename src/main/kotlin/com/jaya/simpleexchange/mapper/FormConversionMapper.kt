package com.jaya.simpleexchange.mapper

import com.jaya.simpleexchange.dto.ConversionForm
import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class FormConversionMapper(
    private val userRepository: UserRepository
): Mapper<ConversionForm, Conversion> {
    override fun map(t: ConversionForm): Conversion {
        return Conversion(
            user = userRepository.getById(t.userId),
            originalCurrency = t.originalCurrency,
            destinyCurrency = t.destinyCurrency,
            amount = t.amount
        )
    }
}