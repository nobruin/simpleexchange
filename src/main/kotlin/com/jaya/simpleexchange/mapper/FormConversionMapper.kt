package com.jaya.simpleexchange.mapper

import com.jaya.simpleexchange.dto.ConversionForm
import com.jaya.simpleexchange.entity.Conversion
import org.springframework.stereotype.Component

@Component
class FormConversionMapper: Mapper<ConversionForm, Conversion> {
    override fun map(t: ConversionForm): Conversion {
        return Conversion(
            userId = t.userId,
            originalCurrency = t.originalCurrency,
            destinyCurrency = t.destinyCurrency,
            amount = t.amount
        )
    }
}