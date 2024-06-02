package com.cl.ui.passedData

data class CheckBalancePassedData(
    val upiID: String,
    val bankName: String,
    val accountNo: String,
    val publicKey: String
)