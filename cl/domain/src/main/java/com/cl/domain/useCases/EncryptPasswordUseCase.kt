package com.cl.domain.useCases

import com.cl.domain.util.cryptography.EncryptionManager

class EncryptPasswordUseCase {
    fun invoke(password: String, publicKey : String) : String {
        val encryptionManager = EncryptionManager(publicKey, "NPCI PUBLIC KEY")
        return encryptionManager.getEncryptedMessage(password)
    }
}