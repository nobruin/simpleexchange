package com.jaya.simpleexchange.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}