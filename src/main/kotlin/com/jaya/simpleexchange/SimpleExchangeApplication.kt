package com.jaya.simpleexchange

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SimpleExchangeApplication

fun main(args: Array<String>) {
	runApplication<SimpleExchangeApplication>(*args)
}
