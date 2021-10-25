package com.jaya.simpleexchange.controller

import com.jaya.simpleexchange.dto.ConversionForm
import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.service.ConversionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/conversions")
@Api(value = "Conversion", description = "Rest API for Conversion operations", tags = ["Conversion API"])
class ConversionController(private val service: ConversionService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Endpoint for creating conversions")
    fun create(@Valid @RequestBody conversionForm: ConversionForm): ResponseEntity<Conversion>{
        val response: Conversion  = service.create(conversionForm)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}