package com.billion_dollor_company.easypay.models

data class RecentInteractionInfo(
    val firstName : String,
    val middleName : String = "",
    val lastName : String,
    val mobileNo : String,
    val upiID : String,
    val userImage : Int,
)