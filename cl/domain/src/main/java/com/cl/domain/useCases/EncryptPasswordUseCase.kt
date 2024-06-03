package com.cl.domain.useCases

import com.cl.domain.util.cryptography.AsymmetricEncryptionManager

class EncryptPasswordUseCase {
    fun invoke(password: String, publicKey : String) : String {
        val asymmetricEncryptionManager =
            AsymmetricEncryptionManager(
                publicKey,
                "NPCI PUBLIC KEY"
            )
        return asymmetricEncryptionManager.getEncryptedMessage(password)
    }
}