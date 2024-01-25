package com.billion_dollor_company.easypay.ui.pin

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinCaptureViewModel @Inject constructor(
) : ViewModel() {
    private var pinEntered = mutableStateOf("")
    private val payeeName = "AKSHAY AVINASH BHALERAO"
    private val payeeAccountNo = "12341234"
    private val payeeBankName = "STATE BANK OF INDIA"

    private val payerName = "ANAND AVINASH BHALERAO"
    private val payerAccountNo = "31241212"
    private val payerBankName = "ICICI"

    private val amount = "100"
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