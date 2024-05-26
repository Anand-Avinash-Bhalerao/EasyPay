package com.billion_dollor_company.easypay.ui.pin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.billion_dollor_company.easypay.models.transaction.TransactionInfo
import com.billion_dollor_company.easypay.ui.pin.components.EnteredPinSection
import com.billion_dollor_company.easypay.ui.pin.components.KeypadSection
import com.billion_dollor_company.easypay.ui.pin.components.MoneyTransferAlertSection
import com.billion_dollor_company.easypay.ui.pin.components.PayeeInfoSection
import com.billion_dollor_company.easypay.ui.pin.components.PayerInfoSection
import com.billion_dollor_company.easypay.util.Screen
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinCaptureScreen(
    passedData: Screen.TransactionPinEnterScreen,
    onSubmitClick: (TransactionInfo) -> Unit
) {
    val viewModel: PinCaptureViewModel = hiltViewModel()
    viewModel.setPassedData(passedData)

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), color = Color.White
        ) {

            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {

                val (payerSection, payeeSection, upiEnterTitle, pinTextFields, paymentAlert, keypad) = createRefs()

                PayerInfoSection(
                    bankName = viewModel.getPayerBankName(),
                    payerAccountNo = viewModel.getPayerAccountNo(),
                    modifier = Modifier
                        .constrainAs(payerSection) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                )


                PayeeInfoSection(
                    viewModel = viewModel,
                    modifier = Modifier
                        .constrainAs(payeeSection) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(payerSection.bottom)
                        }
                )


                Text(
                    text = "ENTER 6 DIGIT UPI PIN",
                    style = MaterialTheme.typography.titleLarge
                        .copy(
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center
                        ),
                    modifier = Modifier
                        .constrainAs(upiEnterTitle) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(payeeSection.bottom, 24.dp)
                        },
                )

                EnteredPinSection(
                    modifier = Modifier
                        .constrainAs(pinTextFields) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(upiEnterTitle.bottom, 18.dp)
                        },
                    viewModel = viewModel
                )

                MoneyTransferAlertSection(
                    payeeName = viewModel.getPayeeName().uppercase(Locale.getDefault()),
                    modifier = Modifier
                        .constrainAs(paymentAlert) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(pinTextFields.bottom)
                            bottom.linkTo(keypad.top, 18.dp)
                        }
                )

                KeypadSection(
                    viewModel = viewModel,
                    modifier = Modifier
                        .constrainAs(keypad) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    onSubmitClick = onSubmitClick
                )
            }
        }
    }
}
