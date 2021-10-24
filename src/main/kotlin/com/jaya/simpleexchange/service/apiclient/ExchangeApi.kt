package com.jaya.simpleexchange.service.apiclient

import com.beust.klaxon.Klaxon
import com.jaya.simpleexchange.config.ExchangeApiProperties
import com.jaya.simpleexchange.dto.ExchangeApiResult
import com.jaya.simpleexchange.entity.Conversion
import org.springframework.beans.factory.annotation.Autowired
import khttp.responses.Response

object ExchangeApi {

    @Autowired
    lateinit var response: Response

    fun getQuoteForCurrencies(originalCurrency: String, destinyCurrency: String, properties: ExchangeApiProperties): ExchangeApiResult{
        var exchangeResponse = ExchangeApiResult()

        response = khttp.get(
            url = properties.uri+"latest",
            params = mapOf(
                "access_key" to properties.secret,
                "symbols" to "${originalCurrency},${destinyCurrency}")
        )

        if(!isResponseEmpty()){
            exchangeResponse = convertJsonToResultObject(response.jsonObject.toString())
        }

        return exchangeResponse
    }

    private fun isResponseEmpty(): Boolean {
        if (response.statusCode != 200 || response.jsonObject.get("success").toString().equals("false"))
            return true

        return false
    }

    private fun convertJsonToResultObject(jsonString: String): ExchangeApiResult {
        val exchangeResponse = Klaxon().parse<ExchangeApiResult>(jsonString)

        if(exchangeResponse is ExchangeApiResult)
            return  exchangeResponse

        return ExchangeApiResult()
    }
}