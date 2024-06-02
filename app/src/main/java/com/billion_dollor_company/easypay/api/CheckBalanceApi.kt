package com.billion_dollor_company.easypay.api

import com.billion_dollor_company.easypay.util.Constants
import com.npciCore.common.models.checkBalance.CheckBalanceReqInfo
import com.npciCore.common.models.checkBalance.CheckBalanceResInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckBalanceApi {

    @POST(Constants.Server.PspServer.CHECK_BALANCE_URL)
    suspend fun getAccountBalance(@Body checkBalanceReqInfo: CheckBalanceReqInfo): Response<CheckBalanceResInfo>

}