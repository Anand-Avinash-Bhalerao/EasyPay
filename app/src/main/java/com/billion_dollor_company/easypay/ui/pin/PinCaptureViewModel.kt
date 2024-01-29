package com.billion_dollor_company.easypay.ui.pin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.api.StartTransactionApi
import com.billion_dollor_company.easypay.api.TransactionResponseInfo
import com.billion_dollor_company.easypay.models.TransactionInfo
import com.billion_dollor_company.easypay.models.UserInfoReq
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
    private val payeeBankName = savedStateHandle.get<String>(Constants.PayeeInfo.BANK_NAME)!!
    private val payeeAccountNo = savedStateHandle.get<String>(Constants.PayeeInfo.ACCOUNT_NO)!!
    private val amount = savedStateHandle.get<String>(Constants.Values.AMOUNT)!!

    private val payerName = "ANAND AVINASH BHALERAO"
    private val payerAccountNo = "31241212912"
    private val payerUpiID = "anandbhalerao@oksbi"
    private val payerBankName = "STATE BANK OF INDIA"

    private val refID = "asvdahtdbgdvbwEf121sdggresa"

    fun getTransactionInfo(): TransactionInfo {
        val encryptionManager =
            EncryptionManager(Constants.Keys.NPCI_PUBLIC_KEY, "NPCI public key")
        val encryptedPassword = encryptionManager.getEncryptedMessage(pinEntered.value)
        val reqObject = TransactionInfo(
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
        return reqObject
    }



    fun setPin(newPin: String) = run {
        pinEntered.value = newPin
    }

    fun getPin(): String = pinEntered.value

    fun getPayeeName(): String = payeeName
    fun getPayeeAccountNo(): String = payeeAccountNo
    fun getPayeeBankName(): String = payeeBankName

    fun getPayerName(): String = payerName
    fun getPayerAccountNo(): String = payerAccountNo
    fun getPayerBankName(): String = payerBankName

    fun getAmount(): String = amount

    fun getRefID(): String = refID


}