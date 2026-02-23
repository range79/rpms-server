package com.range.rpms.common.enums

enum class MailType(
    val template: String,
    val subject: String
) {

    VERIFY_ACCOUNT(
        template = "verify-email",
        subject = "Verify your account"
    ),

    RESET_PASSWORD(
        template = "reset-password",
        subject = "Reset your password"
    ),
    SECURITY_ALERT(
        template = "security-alert",
        subject = "New login detected"
    );
 
}