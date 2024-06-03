package com.core.network

object NetworkConstants {
    object PspServer {
        const val USER_INFO_URL = "accountInfo"
        const val TRANSACTION_URL = "transaction"
        const val CHECK_BALANCE_URL = "checkBalance"
        const val REGISTRATION_URL = "registration"
        const val FETCH_KEYS_URL = "fetchKeys"

        fun getBaseURL(ipAddress: String) = "http://$ipAddress:16000/psp/"
    }
}