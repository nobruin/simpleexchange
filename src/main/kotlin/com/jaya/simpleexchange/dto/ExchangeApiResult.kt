package com.jaya.simpleexchange.dto

import java.util.*

data class ExchangeApiResult(
    var success: Boolean = true,
    var timestamp: Long = 0,
    var base: String = "",
    var date: String = "",
    var rates: Map<String, Double> = mutableMapOf()
)