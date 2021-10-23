package com.jaya.simpleexchange.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.jaya.simpleexchange.entity.validation.IsStrCurrencyValid
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.*

@Entity
@Table(name = "conversions")
data class Conversion(
    @Id
    @GeneratedValue
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long? = null,
    @field:NotNull(message = "is mandatory")
    val userId: Long? = null,
    @field:NotNull(message = "is mandatory")
    @field:DecimalMin("0.0000000001", message = " must be greater than 0")
    val amount: Double = 0.0,
    @field:NotBlank
    @field:Size(min =3, max = 3, message = " must have 3 characters ")
    @IsStrCurrencyValid
    val originalCurrency: String = "",
    @field:NotBlank
    @field:Size(min =3, max = 3, message = " must have 3 characters ")
    @IsStrCurrencyValid
    val destinyCurrency: String = "",
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    var rateConversion: Double? = null,
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    var convertedAmount: Double = 0.0
)