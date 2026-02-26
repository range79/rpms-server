package com.range.rpms.repository.crypto

import com.range.rpms.repository.exception.InvalidSshPublicKeyException
import java.security.MessageDigest
import java.util.Base64

object SshFingerprint {

    private val SUPPORTED_TYPES = setOf(
        "ssh-rsa",
        "ssh-ed25519",
        "ecdsa-sha2-nistp256",
        "ecdsa-sha2-nistp384",
        "ecdsa-sha2-nistp521",
        "sk-ssh-ed25519@openssh.com",
        "sk-ecdsa-sha2-nistp256@openssh.com"
    )

    fun sha256(publicKey: String): String =
        "SHA256:" + base64Digest(publicKey, "SHA-256", noPadding = true)

    fun sha1(publicKey: String): String =
        "SHA1:" + base64Digest(publicKey, "SHA-1", noPadding = true)

    fun md5(publicKey: String): String =
        "MD5:" + hexColonDigest(publicKey, "MD5")

    fun detectDefault(publicKey: String): String {

        return sha256(publicKey)
    }

    private fun base64Digest(publicKey: String, algorithm: String, noPadding: Boolean): String {
        val keyBytes = extractKeyBlob(publicKey)
        val digest = MessageDigest.getInstance(algorithm).digest(keyBytes)

        val encoder = if (noPadding)
            Base64.getEncoder().withoutPadding()
        else
            Base64.getEncoder()

        return encoder.encodeToString(digest)
    }

    private fun hexColonDigest(publicKey: String, algorithm: String): String {
        val keyBytes = extractKeyBlob(publicKey)
        val digest = MessageDigest.getInstance(algorithm).digest(keyBytes)

        return digest.joinToString(":") {
            "%02x".format(it)
        }
    }

    private fun extractKeyBlob(publicKey: String): ByteArray {
        val parts = publicKey.trim().split(Regex("\\s+"))

        if (parts.size < 2) {
            throw InvalidSshPublicKeyException("Invalid SSH public key format")
        }

        val keyType = parts[0]
        val keyBase64 = parts[1]

        if (keyType !in SUPPORTED_TYPES) {
            throw InvalidSshPublicKeyException("Unsupported SSH key type: $keyType")
        }

        return try {
            Base64.getDecoder().decode(keyBase64)
        } catch (e: IllegalArgumentException) {
            throw InvalidSshPublicKeyException("Invalid SSH public key base64 content")
        }
    }
}