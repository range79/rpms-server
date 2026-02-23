package com.range.rpms.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@ConfigurationProperties(prefix = "rpms.mail")
data class MailProperties(
    var from: String,
    var name: String
)