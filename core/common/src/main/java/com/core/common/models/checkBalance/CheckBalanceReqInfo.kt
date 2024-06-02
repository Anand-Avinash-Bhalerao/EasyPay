package com.core.common.models.checkBalance

data class CheckBalanceReqInfo(
    val upiID : String,
    val encryptedPassword : String,
)