package com.jaya.simpleexchange.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.entity.validation.IsStrCurrencyValid
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ConversionForm(
    @ApiModelProperty(
        value = "amount that will be converted into another currency",
        name = "amount",
        example = "10"
    )
    @field:NotNull(message = "is mandatory")
    @field:DecimalMin("0.01")
    val amount: BigDecimal = "0.0".toBigDecimal(),
    @ApiModelProperty(
        value = "It is a word composed of 3 letters, which represents a currency in the international standard defined by ISO 4217.\n Its the currency that will be converted ",
        name = "original_currency",
        example = "USD"
    )
    @field:NotBlank
    @field:Size(min =3, max = 3, message = " must have 3 characters ")
    @field:JsonProperty("original_currency")
    @IsStrCurrencyValid
    val originalCurrency: String = "",
    @ApiModelProperty(
        value = "It is the currency that will be used for the base currency conversion.",
        name = "original_currency",
        example = "BRL"
    )
    @field:JsonProperty("destiny_currency")
    @field:NotBlank
    @field:Size(min =3, max = 3, message = " must have 3 characters ")
    @IsStrCurrencyValid
    val destinyCurrency: String = "",
    @ApiModelProperty(
        value = "Is the id of a valid user.",
        name = "user_id",
        example = "1"
    )
    @field:NotNull(message = "is mandatory")
    @field:JsonProperty("user_id")
    val userId: Long? = null
){
    fun toConversionEntity(): Conversion {
        return Conversion(
            userId = userId,
            originalCurrency = originalCurrency,
            destinyCurrency = destinyCurrency,
            amount = amount
        )
    }
}