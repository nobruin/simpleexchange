package com.jaya.simpleexchange.entity

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "conversions")
data class Conversion(
    @Id
    @GeneratedValue
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long? = null,
    val userId: Long? = null,
    val amount: Double = 0.0,
    val originalCurrency: String = "",
    val destinyCurrency: String = "",
    var rateConversion: Double? = null,
    var convertedAmount: Double = 0.0
)