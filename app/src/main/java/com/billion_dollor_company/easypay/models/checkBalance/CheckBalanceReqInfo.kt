package com.billion_dollor_company.easypay.models.checkBalance

data class CheckBalanceReqInfo(
    val upiID : String,
    val encryptedPassword : String,
)