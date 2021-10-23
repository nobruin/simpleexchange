package com.jaya.simpleexchange.handler

data class ErrorView(
    val statusCode: Int,
    val error: String?,
    val path: String
)