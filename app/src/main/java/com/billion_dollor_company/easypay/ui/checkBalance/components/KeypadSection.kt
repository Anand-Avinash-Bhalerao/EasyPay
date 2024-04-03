package com.billion_dollor_company.easypay.ui.checkBalance.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.models.checkBalance.CheckBalanceInfo
import com.billion_dollor_company.easypay.ui.checkBalance.CheckBalanceViewModel
import com.billion_dollor_company.easypay.ui.theme.light_grey
import com.billion_dollor_company.easypay.ui.theme.pin_enter_keypad_color


private const val CLEAR = "CLEAR"
private const val SUBMIT = "SUBMIT"

@Composable
fun KeypadSection(
    viewModel: CheckBalanceViewModel,
    modifier: Modifier,
    onSubmitClick: (CheckBalanceInfo) -> Unit
) {

    val callback = { text: String ->
        val currentPinEntered = viewModel.getPin()
        if (text == CLEAR) {
            if (currentPinEntered.isNotEmpty()) {
                val newText =
                    currentPinEntered.subSequence(0, currentPinEntered.length - 1).toString()
                viewModel.setPin(newText)
            } else {
            }
        } else if (text == SUBMIT) {
            if (currentPinEntered.length == 6) {
                val transactionInfo = viewModel.getCheckBalanceInfo()
                onSubmitClick(transactionInfo)
            }
        } else {
            if (currentPinEntered.length != 6) {
                viewModel.setPin(currentPinEntered + text)
            } else {
            }
        }
    }

    Column(
        modifier = modifier
            .background(color = light_grey)
            .padding(top = 6.dp, bottom = 12.dp),
    ) {
        NumberRow(
            listOf("1", "2", "3"),
            callback
        )
        NumberRow(
            listOf("4", "5", "6"),
            callback
        )
        NumberRow(
            listOf("7", "8", "9"),
            callback
        )
        BottomRow(
            callback = callback
        )
    }
}

@Composable
private fun NumberRow(
    texts: List<String>,
    callback: (text: String) -> Any
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in texts.indices) {
            NumberButton(
                text = texts[i],
                modifier = Modifier.weight(0.33f),
                callback = callback
            )
        }
    }
}

@Composable
private fun BottomRow(
    callback: (text: String) -> Any
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {


        OperationButton(
            callback = callback,
            modifier = Modifier
                .weight(0.33f)
                .align(Alignment.CenterVertically),
            buttonIcon = R.drawable.backspace,
            operationType = CLEAR,
            iconSize = 36
        )

        NumberButton(
            text = "0",
            modifier = Modifier
                .weight(0.33f)
                .align(Alignment.CenterVertically),
            callback = callback
        )

        OperationButton(
            callback = callback,
            modifier = Modifier
                .weight(0.33f)
                .align(Alignment.CenterVertically),
            buttonIcon = R.drawable.circle_check,
            operationType = SUBMIT,
            iconSize = 60
        )

    }
}


@Composable
private fun OperationButton(
    callback: (text: String) -> Any,
    modifier: Modifier,
    buttonIcon: Int,
    operationType: String,
    iconSize: Int
) {


    TextButton(
        onClick = {
            callback(operationType)
        },
        modifier = modifier,
//            .height(60.dp),
        shape = RectangleShape
    ) {

        Icon(
            painter = painterResource(id = buttonIcon),
            contentDescription = operationType,
            tint = pin_enter_keypad_color,
            modifier = Modifier
                .size(iconSize.dp)
        )
    }
}


@Composable
private fun NumberButton(
    text: String,
    callback: (text: String) -> Any,
    modifier: Modifier = Modifier
) {
    TextButton(
        modifier = modifier
            .height(65.dp),
        onClick = {
            callback(text)
        },
        shape = RectangleShape,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = pin_enter_keypad_color,
        )
    }
}
