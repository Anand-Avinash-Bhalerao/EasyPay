package com.billion_dollor_company.easypay.ui.checkBalance.checkBalanceRequester

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.Helper
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckBalanceRequesterScreen(
    isPasswordFetched: Boolean,
    onBackPress: () -> Unit,
    navigateToCL: () -> Unit,
    navigateToCompleteScreen: () -> Unit
) {

    var isAccCardClicked by remember {
        mutableStateOf(isPasswordFetched)
    }

    Scaffold(
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = {
                        Text(text = "Check Balance")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onBackPress()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val (headingLabel, accountCard, upiLogo) = createRefs()

                Text(
                    text = "Your Accounts:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .constrainAs(headingLabel) {
                            top.linkTo(parent.top, 16.dp)
                            start.linkTo(parent.start, 24.dp)
                        }
                )

                Card(
                    onClick = {
                        isAccCardClicked = true
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .constrainAs(accountCard) {
                            top.linkTo(headingLabel.bottom, 12.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .height(65.dp)
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(30.dp),
                            painter = painterResource(id = Helper.getBankIcon()),
                            contentDescription = "Logo of the bank: ${Constants.PayerDetails.BANK_NAME}"
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "${Constants.PayerDetails.BANK_NAME} - ${
                                Constants.PayerDetails.ACCOUNT_NO.takeLast(
                                    4
                                )
                            }",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .constrainAs(upiLogo) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    painter = painterResource(id = R.drawable.upi_logo),
                    contentDescription = "Powered by UPI"
                )
            }
        }

        if (isAccCardClicked) {
            Dialog(
                onDismissRequest = {

                }
            ) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (!isPasswordFetched) "Setting up PIN Capture.." else "Initializing your request..",
                            modifier = Modifier
                                .align(Alignment.CenterStart),
                            style = MaterialTheme.typography.titleMedium
                        )
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                        )
                    }
                }
            }
            LaunchedEffect(Unit) {
                delay(1000)
                if (!isPasswordFetched) {
                    navigateToCL()
                } else {
                    navigateToCompleteScreen()
                }
            }
        }

    }
}
