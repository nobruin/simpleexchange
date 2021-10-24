package com.jaya.simpleexchange.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.jaya.simpleexchange.entity.validation.IsStrCurrencyValid
import java.math.BigDecimal
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
    @field:DecimalMin("0.01")
    val amount: BigDecimal = "0.0".toBigDecimal(),
    @field:NotBlank
    @field:Size(min =3, max = 3, message = " must have 3 characters ")
    @IsStrCurrencyValid
    val originalCurrency: String = "",
    @field:NotBlank
    @field:Size(min =3, max = 3, message = " must have 3 characters ")
    @IsStrCurrencyValid
    val destinyCurrency: String = "",
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    var rateConversion: BigDecimal? = null,
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    var convertedAmount: BigDecimal = "0.0".toBigDecimal()
)