package com.jaya.simpleexchange.repository

import com.jaya.simpleexchange.entity.Conversion
import org.springframework.data.jpa.repository.JpaRepository

interface ConversionRepository : JpaRepository<Conversion, Long>