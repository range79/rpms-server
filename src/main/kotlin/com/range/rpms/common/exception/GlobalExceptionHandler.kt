package com.range.rpms.common.exception

import com.range.rpms.common.dto.ExceptionResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler
{
    @ExceptionHandler(_root_ide_package_.com.range.rpms.common.exception.BaseException::class)
    fun handleBaseException(ex: com.range.rpms.common.exception.BaseException,
                            request: HttpServletRequest
    ): com.range.rpms.common.dto.ExceptionResponse {
        return _root_ide_package_.com.range.rpms.common.dto.ExceptionResponse(
            ex.localizedMessage,
            request.requestURI,
            ex.status
        )
    }
}