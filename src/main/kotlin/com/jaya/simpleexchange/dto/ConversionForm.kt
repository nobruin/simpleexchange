package com.jaya.simpleexchange.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.jaya.simpleexchange.entity.validation.IsStrCurrencyValid
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ConversionForm(
    @field:NotNull(message = "is mandatory")
    @field:DecimalMin("0.01")
    val amount: BigDecimal = "0.0".toBigDecimal(),
    @field:NotBlank
    @field:Size(min =3, max = 3, message = " must have 3 characters ")
    @field:JsonProperty("original_currency")
    @IsStrCurrencyValid
    val originalCurrency: String = "",
    @field:JsonProperty("destiny_currency")
    @field:NotBlank
    @field:Size(min =3, max = 3, message = " must have 3 characters ")
    @IsStrCurrencyValid
    val destinyCurrency: String = "",
    @field:NotNull(message = "is mandatory")
    @field:JsonProperty("user_id")
    val userId: Long? = null
)