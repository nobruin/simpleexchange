package com.jaya.simpleexchange.repository

import com.jaya.simpleexchange.entity.Conversion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ConversionRepository : JpaRepository<Conversion, Long> {
    fun findAllByUserId(userId: Long, pageable: Pageable): Page<Conversion>?
    fun existsByUserId(userId: Long): Boolean
}