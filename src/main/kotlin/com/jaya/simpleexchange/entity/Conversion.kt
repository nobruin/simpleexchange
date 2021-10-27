package com.jaya.simpleexchange.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.catalina.User
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import javax.persistence.*

@Entity
data class Conversion(
    @Id
    @GeneratedValue
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long? = null,
    val amount: BigDecimal = "0.0".toBigDecimal(),
    @field:JsonProperty("original_currency")
    val originalCurrency: String = "",
    @field:JsonProperty("destiny_currency")
    val destinyCurrency: String = "",
    @field:JsonProperty("rate_conversion")
    var rateConversion: BigDecimal? = null,
    @field:JsonProperty("converted_amount")
    var convertedAmount: BigDecimal = "0.0".toBigDecimal(),
    @field:JsonProperty("created_at")
    val createdAt: OffsetDateTime = LocalDateTime.now().atOffset(ZoneOffset.UTC),
    @ManyToOne
    val user: User
)