package com.jaya.simpleexchange.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.jaya.simpleexchange.entity.Conversion
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

    @Test
    fun `test create new conversion`(){
        val conversion = Conversion(
            amount = 20.0,
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
            .andExpect(MockMvcResultMatchers.jsonPath("\$.value").value(conversion.amount))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.originalCurrency").value(conversion.originalCurrency))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.destinyCurrency").value(conversion.destinyCurrency))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.userId").value(conversion.userId))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.rateConversion").value(conversion.rateConversion))
            .andDo(MockMvcResultHandlers.print())
    }
}