package com.jaya.simpleexchange.service

import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.repository.ConversionRepository
import org.springframework.stereotype.Service

@Service
class ConversionService(private val repository: ConversionRepository) {
    fun create(conversion: Conversion): Conversion {


        return repository.save(conversion)
    }
}
