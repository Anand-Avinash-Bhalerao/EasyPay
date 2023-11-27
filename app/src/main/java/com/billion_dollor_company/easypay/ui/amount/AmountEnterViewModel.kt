package com.billion_dollor_company.easypay.ui.amount

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel

class AmountEnterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val name = savedStateHandle.get<String>(Constants.NAME)!!
    val phoneNo = savedStateHandle.get<String>(Constants.PHONE_NO)!!
    val upiID = savedStateHandle.get<String>(Constants.UPI_ID)!!

    private var amountEntered = mutableStateOf("")

    fun setAmount(newValue: String) = run { amountEntered.value = newValue }
    fun getAmount(): String = amountEntered.value
    fun getAmountFormatted(): String {
        val currentText = getAmount()
        val formatter = DecimalFormat("##,##,##,##,###.##")
        var formattedString = ""
        if (currentText.contains(".")) {
            val indexOfDecimal = currentText.indexOf(".")
            var firstHalf = currentText.subSequence(0, indexOfDecimal).toString()
            firstHalf = formatter.format(firstHalf.toDouble())
            formattedString = "₹ $firstHalf."
            var secondHalf = ""
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