package com.jaya.simpleexchange.handler

import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
        exception: Exception,
        request: HttpServletRequest
    ): ErrorView = ErrorView(
        errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
        error = exception.message,
        path = request.servletPath
    )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorView {
        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.map {
                e -> errorMessage.put(e.field, e.defaultMessage)
        }

        return ErrorView(
            errorCode = HttpStatus.BAD_REQUEST.value(),
            error = errorMessage.toString(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleReadableError(
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            errorCode = HttpStatus.BAD_REQUEST.value(),
            error = "Json Body error: check the requisition body",
            path = request.servletPath
        )
    }
}