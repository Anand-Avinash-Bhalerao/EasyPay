package com.core.network.models.checkBalance

data class CheckBalanceReqInfo(
    val upiID : String,
    val encryptedPassword : String,
)