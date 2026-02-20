package com.example.rpms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RangePackageManagerServerApplication

fun main(args: Array<String>) {
    runApplication<RangePackageManagerServerApplication>(*args)
}
