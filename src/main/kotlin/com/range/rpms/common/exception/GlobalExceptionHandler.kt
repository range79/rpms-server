package com.range.rpms.common.exception

import com.range.rpms.common.dto.ExceptionResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler
{
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
}