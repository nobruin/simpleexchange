package com.jaya.simpleexchange.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ExchangeApiProperties {
    @Value("\${app.uri}")
    lateinit var uri: String

    @Value("\${app.secret}")
    lateinit var secret: String
}