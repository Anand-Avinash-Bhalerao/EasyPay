package com.billion_dollor_company.easypay.ui.pin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.util.Constants
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
    private val amount = savedStateHandle.get<String>(Constants.PayeeInfo.AMOUNT)!!

    private val payerName = "ANAND AVINASH BHALERAO"
    private val payerAccountNo = "31241212"
    private val payerBankName = "ICICI"

    private val refID = "asdad123"


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