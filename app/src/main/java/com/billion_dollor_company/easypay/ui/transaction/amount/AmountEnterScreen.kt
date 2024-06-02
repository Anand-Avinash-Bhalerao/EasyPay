package com.billion_dollor_company.easypay.ui.transaction.amount

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.billion_dollor_company.easypay.models.PinCaptureReqInfo
import com.billion_dollor_company.easypay.ui.components.HeightSpacer
import com.billion_dollor_company.easypay.ui.components.WidthSpacer
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountEnterScreen(
    passedData: Screen.AmountEnterScreen,
    onBackClick: () -> Unit,
    onPayClick: (pinCaptureReqInfo: PinCaptureReqInfo) -> Unit
) {
    val viewModel: AmountEnterViewModel = hiltViewModel()
    viewModel.setPassedData(passedData)
    Scaffold(
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = {
                        Text(text = "Send Money")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onBackClick()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val (payeeInfoSection, amountEnterSection) = createRefs()
                PayeeDetailsSections(
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(payeeInfoSection) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(amountEnterSection.top)
                        }
                )
                AmountAndKeypadSection(
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(amountEnterSection) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    onPayClick = onPayClick
                )
            }
        }
    }
}

@Composable
fun PayeeDetailsSections(
    viewModel: AmountEnterViewModel,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = viewModel.getPayeeImageID()),
            contentDescription = "Payee image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        HeightSpacer()
        Text(
            text = viewModel.getPayeeName(),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        HeightSpacer(6)
        Text(
            text = "+91 ${viewModel.getPayeePhoneNo()}",
        )
        HeightSpacer(6)
        Text(
            text = viewModel.getPayeeUpiID(),
        )
    }
}

@Composable
fun AmountAndKeypadSection(
    viewModel: AmountEnterViewModel,
    modifier: Modifier = Modifier,
    onPayClick: (transactionReqInfo: PinCaptureReqInfo) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Divider()
        HeightSpacer(12)
        Text(
            text = viewModel.getAmountFormatted(),
            style = MaterialTheme.typography.displaySmall
        )
        HeightSpacer(12)
        Divider()
        HeightSpacer(12)
        Keypad(viewModel = viewModel)

        Button(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.viewModelScope.launch {
                    Log.d(Constants.TAG, "Before calling the fn")
                    val details = viewModel.getDetails(viewModel.getPayeeUpiID())
                    Log.d(Constants.TAG, "after calling the fn")
                    onPayClick(details)
                }

            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Payment,
                    contentDescription = "Pay Icon"
                )
                WidthSpacer(12)
                Text(text = "Pay")
            }

        }
    }
}

@Composable
private fun Keypad(
    viewModel: AmountEnterViewModel
) {

    val callback = { text: String ->
        val currentText = viewModel.getAmount()
        if (text == "clear") {
            if (currentText.isNotEmpty()) {
                val newText = currentText.subSequence(0, currentText.length - 1).toString()
                viewModel.setAmount(newText)
            }
        } else if (text == ".") {
            if (!currentText.contains('.'))
                viewModel.setAmount(currentText + text)
        } else {
            if (currentText.contains(".")) {
                val index = currentText.indexOf(".")
                val length = currentText.length
                if (length - index < 3) {
                    viewModel.setAmount(currentText + text)
                }
            } else {
                viewModel.setAmount(currentText + text)
            }
        }
    }

    Column {
        MyRow(
            listOf("1", "2", "3"),
            callback
        )
        MyRow(
            listOf("4", "5", "6"),
            callback
        )
        MyRow(
            listOf("7", "8", "9"),
            callback
        )
        BottomRow(
            callback = callback
        )
    }
}

@Composable
fun BottomRow(
    callback: (text: String) -> Any
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        MyButton(
            text = ".",
            modifier = Modifier.weight(0.33f),
            callback = callback
        )
        MyButton(
            text = "0",
            modifier = Modifier.weight(0.33f),
            callback = callback
        )
        IconButton(
            onClick = {
                callback("clear")
            },
            modifier = Modifier
                .weight(0.33f)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Backspace,
                contentDescription = "Delete"
            )
        }
    }
}


@Composable
fun MyRow(
    texts: List<String>,
    callback: (text: String) -> Any
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in texts.indices) {
            MyButton(
                text = texts[i],
                modifier = Modifier.weight(0.33f),
                callback = callback
            )
        }
    }
}

@Composable
fun MyButton(
    text: String,
    callback: (text: String) -> Any,
    modifier: Modifier = Modifier
) {
    TextButton(
        modifier = modifier
            .padding(4.dp),
        onClick = {
            callback(text)
        },
        shape = RectangleShape,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}