package com.billion_dollor_company.easypay.models

data class UserInfo(
    val accountNo: String,
    val bankName: String,
    val fullName: String,
    val upiID: String
)