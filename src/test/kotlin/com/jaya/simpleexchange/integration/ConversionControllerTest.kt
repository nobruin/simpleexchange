package com.jaya.simpleexchange.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.jaya.simpleexchange.entity.Conversion
import com.jaya.simpleexchange.service.ConversionService
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class ConversionControllerTest {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var conversionService: ConversionService

    @Test
    fun `test create new conversion`(){
        val conversion = Conversion(
            amount = "20.0".toBigDecimal(),
            originalCurrency = "BRL",
            destinyCurrency = "USD",
            userId = 1
        )
        val jsonObject = ObjectMapper().writeValueAsString(conversion)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/conversions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.id").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.amount").value(conversion.amount))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.originalCurrency").value(conversion.originalCurrency))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.destinyCurrency").value(conversion.destinyCurrency))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.userId").value(conversion.userId))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.rateConversion").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.convertedAmount").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `test create new conversion with error currencies String with more than 3 characters`(){
        val conversion = Conversion(
            amount = "1.01".toBigDecimal(),
            originalCurrency = "BRLs",
            destinyCurrency = "USDs",
            userId = 1
        )
        val jsonObject = ObjectMapper().writeValueAsString(conversion)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/conversions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.errorCode").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.errorCode").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.error").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.path").isString)
            .andDo(MockMvcResultHandlers.print())
    }


    @Test
    fun `test create new conversion with error currencies String invalid`(){
        val conversion = Conversion(
            amount = "1.01".toBigDecimal(),
            originalCurrency = "CAR",
            destinyCurrency = "XPO",
            userId = 1
        )
        val jsonObject = ObjectMapper().writeValueAsString(conversion)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/conversions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.errorCode").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.errorCode").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.error").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.path").isString)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `test create new conversion with error invalid userid value`(){

        val jsonObject = JSONObject("""{"amount":2, "originalCurrency":"BRL", "destinyCurrency":"USD", "userId":"TR"}""")

        mockMvc.perform(
            MockMvcRequestBuilders.post("/conversions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString()))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.errorCode").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.errorCode").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.error").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.path").isString)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `test create new conversion with error invalid amount`(){
        val conversion = Conversion(
            amount = "0.0".toBigDecimal(),
            originalCurrency = "BRL",
            destinyCurrency = "USD",
            userId = 1
        )
        val jsonObject = ObjectMapper().writeValueAsString(conversion)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/conversions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.errorCode").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.errorCode").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.error").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.error").value("{amount=must be greater than or equal to 0.01}"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.path").isString)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `test get all conversions by user_id`(){

        val createdConversion = conversionService.create(Conversion(
            amount = "1".toBigDecimal(),
            originalCurrency = "BRL",
            destinyCurrency = "USD",
            userId = 1
        ))

        mockMvc.perform(MockMvcRequestBuilders.get("/users/${createdConversion.userId}/conversions"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.content").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.content[0].id").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.content[0].amount").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.content[0].originalCurrency").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.content[0].destinyCurrency").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.content[0].userId").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.content[0].rateConversion").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.content[0].convertedAmount").isNumber)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }
}