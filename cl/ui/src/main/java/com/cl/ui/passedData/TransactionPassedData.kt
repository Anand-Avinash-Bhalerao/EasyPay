package com.cl.ui.passedData

import kotlinx.serialization.Serializable

data class TransactionPassedData(
    val payeeUpiID: String,
    val payeeFullName: String,
    val payerUpiID: String,
    val payerBankName: String,
    val payerAccountNumber: String,
    val amountToPay: String
)
