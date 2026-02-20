package com.example.rpms.common.dto

import org.springframework.http.HttpStatus

data class ExceptionResponse (
    val message:String,
    val path:String,
    val status: HttpStatus,
)