package com.cl.ui.screens.transaction

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.cl.ui.passedData.TransactionPassedData
import com.cl.ui.screens.transaction.components.EnteredPinSection
import com.cl.ui.screens.transaction.components.KeypadSection
import com.npciCore.common.models.transaction.TransactionInfo
import com.cl.ui.screens.transaction.components.MoneyTransferAlertSection
import com.cl.ui.screens.transaction.components.PayeeInfoSection
import com.cl.ui.screens.transaction.components.PayerInfoSection
import java.util.Locale

@Composable
fun TransactionPinScreen(
    passedData: TransactionPassedData,
    onSubmitClick: (TransactionInfo) -> Unit
) {
    val viewModel: TransactionPinViewModel = hiltViewModel()
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
