package com.billion_dollor_company.easypay.ui.transaction.amount

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.models.PinCaptureReqInfo
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.navigation.Screen
import com.core.network.models.userInfo.UserReqInfo
import com.core.network.api.UserInfoApi
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel

class AmountEnterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val userInfoApi: UserInfoApi
) : ViewModel() {

    // this data is passed by the navigation components
    private var payeeName = ""
    private var payeePhoneNo = ""
    private var payeeUpiID = ""
    private var payeeImageID = 0


    fun setPassedData(passedData: Screen.AmountEnterScreen) {
        payeeName = passedData.fullName
        payeePhoneNo = passedData.phoneNo
        payeeUpiID = passedData.upiID
        payeeImageID = passedData.imageID
    }


    // create getters for the four variables above
    fun getPayeeName(): String = payeeName
    fun getPayeePhoneNo(): String = payeePhoneNo
    fun getPayeeUpiID(): String = payeeUpiID
    fun getPayeeImageID(): Int = payeeImageID


    // used for storing the amount entered by the user.
    private var amountEntered = mutableStateOf("")

    // calls the backend to fetch the accountInfo of the payee.
    suspend fun getDetails(payeeUPI: String): PinCaptureReqInfo {

        val reqObject = UserReqInfo(payeeUPI)
        val payeeDetails = userInfoApi.getUserInfo(reqObject).body()!!

        var payeeFullname = "${payeeDetails.firstName} "
        if (payeeDetails.middleName.isNotEmpty()) payeeFullname += "${payeeDetails.middleName} "
        payeeFullname += payeeDetails.lastName
        val details = PinCaptureReqInfo(
            payeeUpiID = payeeDetails.upiID,
            payeeFullName = payeeFullname,
            payerUpiID = Constants.PayerDetails.UPI_ID,
            accountNo = Constants.PayerDetails.ACCOUNT_NO,
            bankName = Constants.PayerDetails.BANK_NAME,
            amountToTransfer = amountEntered.value.toDouble().toString(),

            )
        Log.d(Constants.TAG, "The response is $payeeDetails")
        return details
    }

    fun setAmount(newValue: String) = run { amountEntered.value = newValue }
    fun getAmount(): String = amountEntered.value
    fun getAmountFormatted(): String {
        val currentText = getAmount()
        val formatter = DecimalFormat("##,##,##,##,###.##")
        var formattedString: String
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