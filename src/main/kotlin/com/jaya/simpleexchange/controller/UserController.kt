package com.jaya.simpleexchange.controller

import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.service.ConversionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val conversionService: ConversionService
) {

    @GetMapping("{id}/conversions")
    fun listConversionByUser(
        @PathVariable id: Long,
        pageable: Pageable
    ): Page<Conversion>?  {
        return conversionService.searchByUserId(userId = id, pageable = pageable)
    }
}