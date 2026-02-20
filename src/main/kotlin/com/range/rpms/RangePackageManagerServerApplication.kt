package com.range.rpms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
@ConfigurationPropertiesScan()
@SpringBootApplication
class RangePackageManagerServerApplication

fun main(args: Array<String>) {
    runApplication<RangePackageManagerServerApplication>(*args)
}
