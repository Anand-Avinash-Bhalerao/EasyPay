package com.billion_dollor_company.easypay.ui.transactionComplete

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.api.StartTransactionApi
import com.billion_dollor_company.easypay.models.transaction.TransactionReqInfo
import com.billion_dollor_company.easypay.models.transaction.TransactionResInfo
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.Helper
import com.billion_dollor_company.easypay.util.Screen
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionCompleteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @Inject
    lateinit var startTransactionApi: StartTransactionApi

    private var payeeUpiID = ""
    private var payeeFullName = ""
    private var payerUpiID = ""
    private var payerBankName = ""
    private var payerAccountNo = ""
    private var encryptedPassword = ""
    private var amount = ""

    private var transactionStatus = Constants.Values.LOADING
    private var transactionMessage = ""


    private fun getTransactionInfo() = TransactionReqInfo(
        payeeUpiID = payeeUpiID,
        payerAccountNo = payerAccountNo,
        payerBankName = payerBankName,
        payerUpiID = payerUpiID,
        amountToTransfer = amount,
        encryptedPassword = encryptedPassword
    )

    suspend fun startTransaction() {
        val transactionInfo = getTransactionInfo()
        withContext(Dispatchers.IO) {
            try {
                // Make your API call here
                val response = startTransactionApi.startTransaction(transactionInfo)
                // Check the response and return true or false accordingly

                var statusInfo: TransactionResInfo = if (response.isSuccessful) {
                    response.body()!!
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<TransactionResInfo>() {}.type
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                }

                transactionStatus = statusInfo.status
                transactionMessage = statusInfo.message

                Log.d(Constants.TAG, "The Response is: $response")

            } catch (e: Exception) {
            }
        }
    }


    fun getTransactionStatus(): String = transactionStatus

    fun getTransactionMessage(): String = transactionMessage

    fun getPayeeName(): String = payeeFullName

    fun getPayeeUpiID(): String = payeeUpiID
    fun getTransferAmount(): String = amount

    fun init(passedData: Screen.TransactionCompleteScreen) {
        payeeUpiID = passedData.payeeUpiID
        payeeFullName = passedData.payeeFullName
        payerUpiID = passedData.payerUpiID
        payerBankName = passedData.payerBankName
        payerAccountNo = passedData.payerAccountNumber
        encryptedPassword = passedData.encryptedPassword
        amount = passedData.amount
    }
}