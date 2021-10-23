package com.jaya.simpleexchange.entity.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CurrencyValidator::class])
@MustBeDocumented

annotation class IsStrCurrencyValid(
    val message: String ="The currency entered is invalid",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)
