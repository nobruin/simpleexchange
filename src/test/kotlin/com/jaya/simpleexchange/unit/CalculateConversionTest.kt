package com.jaya.simpleexchange.unit

import com.jaya.simpleexchange.util.ConversionUtil
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CalculateConversionTest {

    @Test
    fun `test Conversion Calculate`(){
        val util = ConversionUtil()

        val usd = 1.16
        val brl = 6.58

        val result = util.calculateConversionRate(usd, brl)?.toDouble()

        assert(result == 5.672413793103448)
    }

    @Test
    fun `test Conversion Calculate with first value is invalid`(){
        val util = ConversionUtil()

        val usd: Double? = null
        val brl = 6.58

        assertThrows<RuntimeException>{ util.calculateConversionRate(usd, brl) }
    }

    @Test
    fun `test Conversion Calculate with second value is invalid`(){
        val util = ConversionUtil()

        val usd: Double = 1.16
        val brl: Double? = null

        assertThrows<RuntimeException>{ util.calculateConversionRate(usd, brl) }
    }

    @Test
    fun `test Conversion Calculate equal to or less than zero`(){
        val util = ConversionUtil()

        val usd: Double = (1.16 * -1)
        val brl: Double = 0.0

        assertThrows<RuntimeException>{ util.calculateConversionRate(usd, brl) }
    }


    @Test
    fun `test Amount Calculate`(){
        val util = ConversionUtil()

        val amount = "0.1".toBigDecimal()
        val rate =  "1.16".toBigDecimal()

        val result = util.calculateAmount(amount, rate)

        assert(result == "0.116".toBigDecimal())
    }

    @Test
    fun `test Amount Calculate with null value`(){
        val util = ConversionUtil()

        val amount = "0.1".toBigDecimal()
        val rate =  null

        assertThrows<RuntimeException> { util.calculateAmount(amount, rate) }
    }

    @Test
    fun `test Amount Calculate equal to zero`(){
        val util = ConversionUtil()

        val amount = "0.1".toBigDecimal()
        val rate =  "0".toBigDecimal()

        assertThrows<RuntimeException> { util.calculateAmount(amount, rate) }
    }

    @Test
    fun `test Amount Calculate less than zero`(){
        val util = ConversionUtil()

        val amount = "0.1".toBigDecimal()
        val rate =  "-1".toBigDecimal()

        assertThrows<RuntimeException> { util.calculateAmount(amount, rate) }
    }

}