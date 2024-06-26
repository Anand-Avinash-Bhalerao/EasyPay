package com.npciCore.common.models.transaction

data class TransactionInfo (
    val payeeUpiID: String,
    val payeeFullName : String = "",
    val payeeMobileNo : String = "",
    val payerAccountNo: String = "",
    val payerBankName: String = "",
    val payerUpiID: String = "",

    val amountToTransfer: String,
    val encryptedPassword: String = ""
)