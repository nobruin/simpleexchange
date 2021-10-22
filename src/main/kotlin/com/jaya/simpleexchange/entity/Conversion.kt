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
    val value: Double? = null,
    val originalCurrency: String? = null,
    val destinyCurrency: String? = null,
    val rateConversion: Double? = null,
    val userId: Long? = null
)