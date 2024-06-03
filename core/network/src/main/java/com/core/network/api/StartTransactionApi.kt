package com.billion_dollor_company.easypay.api

import com.core.network.models.transaction.TransactionReqInfo
import com.core.network.models.transaction.TransactionResInfo
import com.core.network.NetworkConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface StartTransactionApi {

    @POST(NetworkConstants.PspServer.TRANSACTION_URL)
    suspend fun startTransaction(@Body transactionReqInfo: TransactionReqInfo): Response<TransactionResInfo>

}