package com.cl.domain.useCases

import android.util.Base64
import com.cl.domain.util.cryptography.SymmetricEncryptionManager
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

internal class AesManagerUseCase() {

    private val symmetricEncryptionManager = SymmetricEncryptionManager()

    fun encrypt(text: String): String {
        return symmetricEncryptionManager.encryptText(text)
    }

    fun decrypt(text: String): String {
        return symmetricEncryptionManager.decryptText(text)
    }

}