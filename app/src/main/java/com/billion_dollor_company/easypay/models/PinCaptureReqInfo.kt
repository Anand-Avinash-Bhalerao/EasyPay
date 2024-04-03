package com.billion_dollor_company.easypay.models

data class PinCaptureReqInfo (
    val payeeFullName : String,
    val payeeUpiID: String,

    val payerUpiID: String,
    val accountNo: String,
    val bankName: String,

    val amountToTransfer: String,
)