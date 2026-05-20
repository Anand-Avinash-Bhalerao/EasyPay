package com.cl.ui.screens.transaction

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.npciCore.common.models.transaction.TransactionInfo
import com.cl.domain.useCases.EncryptPasswordUseCase
import com.cl.ui.passedData.TransactionPassedData
import com.cl.ui.util.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionPinViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val encryptPasswordUseCase: EncryptPasswordUseCase
) : ViewModel() {

    private var pinEntered = mutableStateOf("")

    //create the above 4 variables again but with default values of ""
    private var payeeName = ""
    private var payeeUpiID = ""
    private var payerBankName = ""
    private var payerAccountNo = ""
    private var amount = ""
    private var payerUpiID = ""

    private val refID = "asvdahtdbgdvbwEf121sdggresa"

    private var publicKey = ""

    fun getTransactionInfo(): TransactionInfo {
        val encryptedPassword = encryptPasswordUseCase.invoke(pinEntered.value, publicKey)
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

    fun getPayerAccountNo(): String = payerAccountNo
    fun getPayerBankName(): String = payerBankName

    fun getAmount(): String = amount

    fun getRefID(): String = refID
    fun setPassedData(passedData: TransactionPassedData) {
        // set the empty variables with the passed data variables
        payeeName = passedData.payeeFullName
        payeeUpiID = passedData.payeeUpiID
        payerBankName = passedData.payerBankName
        payerAccountNo = passedData.payerAccountNumber
        payerUpiID = passedData.payerUpiID
        amount = passedData.amountToPay
        publicKey = Helper.decodeSpecialCharString(passedData.publicKey)
    }


}