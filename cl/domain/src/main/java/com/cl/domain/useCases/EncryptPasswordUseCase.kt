package com.cl.domain.useCases

import com.cl.domain.util.cryptography.EncryptionManager

class EncryptPasswordUseCase(private val encryptionManager: EncryptionManager) {
    fun invoke(password: String) : String {
        return encryptionManager.getEncryptedMessage(password)
    }
}