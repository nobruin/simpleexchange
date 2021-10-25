package com.jaya.simpleexchange.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.service.Contact


@Configuration
@EnableSwagger2
open class SwaggerConfig {

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.jaya.simpleexchange.controller"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun getApiInfo(): ApiInfo {
        val contact = Contact("Bruno marins", "", "nobruin@gmail.com")
        return ApiInfoBuilder()
            .title("Simple Exchange")
            .description("Api for simple currency conversion")
            .version("1.0.0")
            .license("Apache 2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
            .contact(contact)
            .build()
    }
}