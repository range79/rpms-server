package com.range.rpms.common.filters

import com.range.rpms.common.dto.DeviceContext
import com.range.rpms.common.dto.DeviceContextHolder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import java.util.UUID

@Component
class DeviceContextFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val deviceId =
            request.getHeader("X-Device-Id")
                ?: UUID.randomUUID().toString()

        val deviceName =
            request.getHeader("X-Device-Name")

        DeviceContextHolder.set(
            DeviceContext(
                deviceId = deviceId,
                deviceName = deviceName
            )
        )

        try {
            filterChain.doFilter(request, response)
        } finally {
            DeviceContextHolder.clear()
        }
    }
}