package com.jaya.simpleexchange.controller

import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.service.ConversionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@Api(value = "user", description = "Rest API for user operations", tags = ["User API"])
class UserController(
    private val conversionService: ConversionService
) {

    @GetMapping("{id}/conversions")
    @ApiOperation(value = "Endpoint for get All conversions by user_id")
    fun listConversionByUser(
        @ApiParam(
            name = "id",
            value = "id for valid user",
            example = "1",
            required = true
        )
        @PathVariable id: Long,
        pageable: Pageable
    ): Page<Conversion>?  {
        return conversionService.searchByUserId(userId = id, pageable = pageable)
    }
}