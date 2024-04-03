package com.billion_dollor_company.easypay.ui.pin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.models.transaction.TransactionInfo
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.cryptography.EncryptionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinCaptureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var pinEntered = mutableStateOf("")

    private val payeeName = savedStateHandle.get<String>(Constants.PayeeInfo.FULL_NAME)!!
    private val payeeUpiID = savedStateHandle.get<String>(Constants.PayeeInfo.UPI_ID)!!
    private val payerBankName = savedStateHandle.get<String>(Constants.PayerInfo.BANK_NAME)!!
    private val payerAccountNo = savedStateHandle.get<String>(Constants.PayerInfo.ACCOUNT_NO)!!
    private val amount = savedStateHandle.get<String>(Constants.Values.AMOUNT)!!

    private val payerName = Constants.PayerDetails.FULL_NAME
    private val payerUpiID = Constants.PayerDetails.UPI_ID

    private val refID = "asvdahtdbgdvbwEf121sdggresa"

    fun getTransactionInfo(): TransactionInfo {
        val encryptionManager =
            EncryptionManager(Constants.Keys.NPCI_PUBLIC_KEY, "NPCI public key")
        val encryptedPassword = encryptionManager.getEncryptedMessage(pinEntered.value)
        return TransactionInfo(
            payeeUpiID = payeeUpiID,
            payeeFullName = payeeName,
            payerUpiID = payerUpiID,
            payerAccountNo = payerAccountNo,
            payerBankName = payerBankName,
            amountToTransfer = amount,
            encryptedPassword = encryptedPassword
        )
    }



    fun setPin(newPin: String) = run {
        pinEntered.value = newPin
    }

    fun getPin(): String = pinEntered.value

    fun getPayeeName(): String = payeeName

    fun getPayerName(): String = payerName
    fun getPayerAccountNo(): String = payerAccountNo
    fun getPayerBankName(): String = payerBankName

    fun getAmount(): String = amount

    fun getRefID(): String = refID


}