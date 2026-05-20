package com.billion_dollor_company.easypay.models

data class PayeeInfo(
    val name: String,
    val phoneNo: String,
    val upiID : String,
    val imageID : Int
)