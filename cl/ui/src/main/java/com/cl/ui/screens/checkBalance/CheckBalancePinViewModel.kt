package com.cl.ui.screens.checkBalance

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cl.domain.useCases.EncryptPasswordUseCase
import com.cl.ui.passedData.CheckBalancePassedData
import com.npciCore.common.Constants
import com.npciCore.common.models.checkBalance.CheckBalanceInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CheckBalancePinViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val encryptPasswordUseCase: EncryptPasswordUseCase
) : ViewModel() {

    private var pinEntered = mutableStateOf("")

    private var userBankName = ""
    private var userAccountNo = ""

    private var userUpiID = ""

    private val refID = "asvdahtdbgdvbwEf121sdggresa"

    fun getCheckBalanceInfo(): CheckBalanceInfo {
        return CheckBalanceInfo(
            upiID = userUpiID,
            encryptedPassword = encryptPasswordUseCase.invoke(pinEntered.value)
        )
    }

    fun setPin(newPin: String) = run {
        pinEntered.value = newPin
    }

    fun getPin(): String = pinEntered.value

    fun getRefID(): String = refID

    fun getAccountNo(): String = userAccountNo

    fun getBankName(): String = userBankName

    fun init(passedData: CheckBalancePassedData) {
        userUpiID = passedData.upiID
        userAccountNo = passedData.accountNo
        userBankName = passedData.bankName

    }

}
