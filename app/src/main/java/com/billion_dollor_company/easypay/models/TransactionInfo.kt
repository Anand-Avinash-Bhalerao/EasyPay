package com.billion_dollor_company.easypay.models

data class TransactionInfo(
    val payeeAccountNo: String,
    val payeeBankName: String,
    val payeeFullName: String,
    val payeeUpiID: String,

    val payerAccountNo: String = "",
    val payerBankName: String = "",
    val payerFullName: String = "",
    val payerUpiID: String = "",

    val amountToTransfer: String,
    val encryptedPassword: String = ""
)