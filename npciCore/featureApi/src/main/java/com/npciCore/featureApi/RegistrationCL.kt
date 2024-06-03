package com.npciCore.featureApi

interface RegistrationCL {
    fun getChallenge(): String
    fun verifyKey(encryptedText: String): Boolean
}