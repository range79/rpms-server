package com.range.rpms.common.exception

import com.range.rpms.common.dto.ExceptionResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.math.log

@RestControllerAdvice
class GlobalExceptionHandler{
    companion object{
        private val LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(ex:BaseException,
                            request: HttpServletRequest
    ): ExceptionResponse {
        return ExceptionResponse(
            ex.localizedMessage,
            request.requestURI,
            ex.status
        )
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ExceptionResponse {

        val firstError = ex.bindingResult
            .fieldErrors
            .firstOrNull()

        val message = firstError?.defaultMessage ?: "Validation failed"

        return ExceptionResponse(
            message,
            request.requestURI,
            HttpStatus.BAD_REQUEST
        )
    }
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: HttpServletRequest): ExceptionResponse {
        LOGGER.error(ex.localizedMessage, ex)
        return ExceptionResponse(
            "Internal server error",
            request.requestURI,
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }
}