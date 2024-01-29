package com.billion_dollor_company.easypay.ui.transactionComplete

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.billion_dollor_company.easypay.api.StartTransactionApi
import com.billion_dollor_company.easypay.models.TransactionInfo
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.Helper
import com.billion_dollor_company.easypay.util.cryptography.EncryptionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class TransactionCompleteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @Inject
    lateinit var startTransactionApi: StartTransactionApi

    private var pinEntered = mutableStateOf("")

    private val payeeName = savedStateHandle.get<String>(Constants.PayeeInfo.FULL_NAME)!!
    private val payeeUpiID = savedStateHandle.get<String>(Constants.PayeeInfo.UPI_ID)!!
    private val payeeBankName = savedStateHandle.get<String>(Constants.PayeeInfo.BANK_NAME)!!
    private val payeeAccountNo = savedStateHandle.get<String>(Constants.PayeeInfo.ACCOUNT_NO)!!

    private val payerName = savedStateHandle.get<String>(Constants.PayerInfo.FULL_NAME)!!
    private val payerUpiID = savedStateHandle.get<String>(Constants.PayerInfo.UPI_ID)!!
    private val payerBankName = savedStateHandle.get<String>(Constants.PayerInfo.BANK_NAME)!!
    private val payerAccountNo = savedStateHandle.get<String>(Constants.PayerInfo.ACCOUNT_NO)!!

    private val encryptedPassword = Helper.decodeSpecialCharString(
        savedStateHandle.get<String>(Constants.Values.ENCRYPTED_PASSWORD)!!
    )
    private val amount = savedStateHandle.get<String>(Constants.Values.AMOUNT)!!

    private var transactionStatus = Constants.Values.LOADING


    private fun getTransactionInfo() = TransactionInfo(
        payeeAccountNo = payeeAccountNo,
        payeeBankName = payeeBankName,
        payeeFullName = payeeName,
        payeeUpiID = payeeUpiID,
        payerAccountNo = payerAccountNo,
        payerBankName = payerBankName,
        payerFullName = payerName,
        payerUpiID = payerUpiID,
        amountToTransfer = amount,
        encryptedPassword = encryptedPassword
    )

    suspend fun startTransaction() {
        val transactionInfo = getTransactionInfo()
        withContext(Dispatchers.IO) {
            try {
                // Make your API call here
                val response = startTransactionApi.startTransaction(transactionInfo).body()!!
                // Check the response and return true or false accordingly

                Log.d(Constants.TAG, "The Response is: $response")
                transactionStatus = response.status
            } catch (e: Exception) {
            }
        }
    }

    fun getTransactionStatus(): String = transactionStatus

}