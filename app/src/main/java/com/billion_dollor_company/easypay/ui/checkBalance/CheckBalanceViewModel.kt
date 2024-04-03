package com.billion_dollor_company.easypay.ui.checkBalance

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.models.checkBalance.CheckBalanceInfo
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.cryptography.EncryptionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CheckBalanceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var pinEntered = mutableStateOf("")

    private val userBankName = Constants.PayerDetails.BANK_NAME
    private val userAccountNo = Constants.PayerDetails.ACCOUNT_NO

    private val userUpiID = Constants.PayerDetails.UPI_ID

    private val refID = "asvdahtdbgdvbwEf121sdggresa"

    fun getCheckBalanceInfo(): CheckBalanceInfo {
        val encryptionManager =
            EncryptionManager(Constants.Keys.NPCI_PUBLIC_KEY, "NPCI public key")
        val encryptedPassword = encryptionManager.getEncryptedMessage(pinEntered.value)
        return CheckBalanceInfo(
            upiID = userUpiID,
            encryptedPassword = encryptedPassword
        )
    }

    fun setPin(newPin: String) = run {
        pinEntered.value = newPin
    }

    fun getPin(): String = pinEntered.value

    fun getRefID(): String = refID

    fun getAccountNo() : String = userAccountNo

    fun getBankName() : String = userBankName

}
