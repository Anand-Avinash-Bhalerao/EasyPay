package com.cl.domain.registration

import android.util.Log
import com.cl.domain.useCases.AesManagerUseCase
import com.npciCore.featureApi.RegistrationCL

class RegistrationImpl : RegistrationCL {

    private val aesManager: AesManagerUseCase = AesManagerUseCase()

    private val original = "7Mkd4QjvZtH8p1XoBn3L"

    override fun getChallenge(): String {
        val encryptedText = aesManager.encrypt(original)
        Log.d("TAGGGG","The encrypted text in registerIMPL is $encryptedText")
        return encryptedText
    }

    override fun verifyKey(encryptedText: String): Boolean {

        val decryptedText = aesManager.decrypt(encryptedText)
        if (decryptedText.length < 10) return false
        val withoutPadding = decryptedText.substring(5, decryptedText.length - 5)
        return withoutPadding == original
    }
}
