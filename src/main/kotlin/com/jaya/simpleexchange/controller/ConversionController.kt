package com.jaya.simpleexchange.controller

import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.service.ConversionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/conversions")
class ConversionController(private val service: ConversionService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody conversion: Conversion): ResponseEntity<Conversion>{
        val response: Conversion  = service.create(conversion)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

}