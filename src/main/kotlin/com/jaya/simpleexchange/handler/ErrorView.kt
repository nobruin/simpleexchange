package com.jaya.simpleexchange.handler

data class ErrorView(
    val errorCode: Int,
    val error: String?,
    val path: String
)