package com.jaya.simpleexchange.dto

data class ExchangeApiResult(
    var success: Boolean = false,
    var timestamp: Long = 0,
    var base: String = "",
    var date: String = "",
    var rates: Map<String, Double> = mutableMapOf()
)