package com.billion_dollor_company.easypay.api

import com.core.network.models.checkBalance.CheckBalanceReqInfo
import com.core.network.models.checkBalance.CheckBalanceResInfo
import com.core.network.NetworkConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckBalanceApi {

    @POST(NetworkConstants.PspServer.CHECK_BALANCE_URL)
    suspend fun getAccountBalance(@Body checkBalanceReqInfo: CheckBalanceReqInfo): Response<CheckBalanceResInfo>

}