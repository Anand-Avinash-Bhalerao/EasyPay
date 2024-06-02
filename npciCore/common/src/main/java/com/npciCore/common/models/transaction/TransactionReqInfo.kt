package com.npciCore.common.models.transaction

data class TransactionReqInfo(
    val payeeUpiID: String,

    val payerAccountNo: String = "",
    val payerBankName: String = "",
    val payerUpiID: String = "",

    val amountToTransfer: String,
    val encryptedPassword: String = ""
)