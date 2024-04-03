package com.billion_dollor_company.easypay.api

import com.billion_dollor_company.easypay.models.transaction.TransactionReqInfo
import com.billion_dollor_company.easypay.models.transaction.TransactionResInfo
import com.billion_dollor_company.easypay.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface StartTransactionApi {

    @POST(Constants.Server.PspServer.TRANSACTION_URL)
    suspend fun startTransaction(@Body transactionReqInfo: TransactionReqInfo): Response<TransactionResInfo>

}