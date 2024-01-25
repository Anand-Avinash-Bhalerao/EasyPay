package com.billion_dollor_company.easypay.ui.amount

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.api.UserInfoApi
import com.billion_dollor_company.easypay.models.TransactionInfo
import com.billion_dollor_company.easypay.models.UserInfoReq
import com.billion_dollor_company.easypay.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel

class AmountEnterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @Inject
    lateinit var userInfoApi: UserInfoApi

    val payeeName = savedStateHandle.get<String>(Constants.PayeeInfo.FULL_NAME)!!
    val payeePhoneNo = savedStateHandle.get<String>(Constants.PayeeInfo.PHONE_NO)!!
    val payeeUpiID = savedStateHandle.get<String>(Constants.PayeeInfo.UPI_ID)!!
    val payeeImageID = savedStateHandle.get<Int>(Constants.PayeeInfo.IMAGE_ID)!!

    private var amountEntered = mutableStateOf("")

    suspend fun getDetails(payeeUPI: String): TransactionInfo {
        val reqObject = UserInfoReq(payeeUPI)
        val payeeDetails = userInfoApi.getUserInfo(reqObject).body()!!

        val details = TransactionInfo(
            payeeFullName = payeeDetails.fullName,
            payeeBankName = payeeDetails.bankName,
            payeeAccountNo = payeeDetails.accountNo,
            payeeUpiID = payeeDetails.upiID,
            amountToTransfer = amountEntered.value,
        )
        Log.d(Constants.TAG, "The response is $payeeDetails")
        return details
    }

    fun setAmount(newValue: String) = run { amountEntered.value = newValue }
    fun getAmount(): String = amountEntered.value
    fun getAmountFormatted(): String {
        val currentText = getAmount()
        val formatter = DecimalFormat("##,##,##,##,###.##")
        var formattedString : String
        if (currentText.contains(".")) {
            val indexOfDecimal = currentText.indexOf(".")
            var firstHalf = currentText.subSequence(0, indexOfDecimal).toString()
            firstHalf = formatter.format(firstHalf.toDouble())
            formattedString = "₹ $firstHalf."
            var secondHalf: String
            if (indexOfDecimal != currentText.length - 1) {
                secondHalf =
                    currentText.subSequence(indexOfDecimal + 1, currentText.length).toString()
                if (secondHalf.length > 2) {
                    secondHalf = secondHalf[0].toString() + secondHalf[1].toString()
                } else if (secondHalf.length == 1) {
                    secondHalf = secondHalf[0].toString()
                }
                formattedString += secondHalf

            }
        } else {
            val number = if (currentText == "") 0 else currentText.toDouble()
            formattedString = "₹ ${formatter.format(number)}"
        }
        return formattedString
    }
}