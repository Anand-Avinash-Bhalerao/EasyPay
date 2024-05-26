package com.billion_dollor_company.easypay.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen() {

    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object ScanScreen : Screen()

    @Serializable
    data class AmountEnterScreen(
        val fullName: String,
        val phoneNo: String,
        val upiID: String,
        val imageID: Int
    ) : Screen()

    @Serializable
    data class TransactionPinEnterScreen(
        val payeeFullName: String,
        val payeeUpiID: String,
        val payerUpiID: String,
        val payerBankName: String,
        val payerAccountNumber: String,
        val amountToPay: String
    ) : Screen()

    @Serializable
    data class TransactionCompleteScreen(
        val payeeUpiID: String,
        val payeeFullName: String,
        val payerUpiID: String,
        val payerBankName: String,
        val payerAccountNumber: String,
        val encryptedPassword: String,
        val amount: String
    ) : Screen()

    @Serializable
    data object CheckBalancePinEnterScreen : Screen()

    @Serializable
    data class CheckBalanceCompleteScreen(
        val upiID: String,
        val encryptedPassword: String
    ) : Screen()

    @Serializable
    data object SelfQRScreen : Screen()
}

