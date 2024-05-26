package com.billion_dollor_company.easypay.ui.checkBalanceComplete

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.model.content.RectangleShape
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.ui.components.HeightSpacer
import com.billion_dollor_company.easypay.ui.transactionComplete.TransactionCompleteViewModel
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.Helper
import com.billion_dollor_company.easypay.util.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckBalanceCompleteScreen(
    passedData: Screen.CheckBalanceCompleteScreen,
    onBackClick: () -> Unit
) {
    val viewModel: CheckBalanceCompleteViewModel = hiltViewModel()
    viewModel.init(passedData)

    var fetchStatus by remember {
        mutableStateOf(viewModel.getStatus())
    }

    LaunchedEffect(Unit) {
        viewModel.viewModelScope.launch {
            delay(1500)
            viewModel.getAccountBalance()
            fetchStatus = viewModel.getStatus()
        }
    }
    val loadingComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_loading))
    val failedComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_fail))
    val moneyComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.wallet_money))
    Scaffold(
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = {
                        Text(text = "Account Balance")
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
            if (fetchStatus == Constants.Values.LOADING) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Fetching bank balance..",
                            style = MaterialTheme.typography.titleLarge.copy(
                                textAlign = TextAlign.Center,
                            )
                        )
                        HeightSpacer(12)
                        LottieAnimation(
                            composition = loadingComposition,
                            iterations = LottieConstants.IterateForever,
                            modifier = Modifier
                                .size(250.dp)
                        )
                    }
                }

            } else {

                if (fetchStatus == Constants.Values.SUCCESS) {

                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val (moneyAnimation, infoSection, doneButton) = createRefs()
                        val middleGuideline = createGuidelineFromTop(0.4f)

                        LottieAnimation(
                            composition = moneyComposition,
                            iterations = LottieConstants.IterateForever,
                            modifier = Modifier
                                .size(250.dp)
                                .constrainAs(moneyAnimation) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top, 24.dp)
                                    bottom.linkTo(infoSection.top)
                                }
                        )


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .constrainAs(infoSection) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(middleGuideline)
                                    bottom.linkTo(doneButton.top)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "Bank account balance fetched successfully",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    textAlign = TextAlign.Center,
                                )
                            )
                            HeightSpacer(8)
                            Text(
                                text = "₹ ${Helper.getFormattedAmount(viewModel.getMessage())}",
                                style = MaterialTheme.typography.displayMedium.copy(
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )
                            HeightSpacer(16)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(viewModel.getBankIcon()),
                                    contentDescription = "Bank Icon",
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = Constants.PayerDetails.BANK_NAME + " - " + Constants.PayerDetails.ACCOUNT_NO.subSequence(
                                        Constants.PayerDetails.ACCOUNT_NO.length - 4,
                                        Constants.PayerDetails.ACCOUNT_NO.length
                                    )
                                )
                            }

                        }


                        Button(
                            onClick = {
                                onBackClick()
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .constrainAs(doneButton) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        ) {
                            Text(text = "Done")
                        }
                    }
                } else {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        val (message) = createRefs()

                        Column(
                            modifier = Modifier
                                .constrainAs(message) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            LottieAnimation(
                                composition = failedComposition,
                                iterations = 1,
                                modifier = Modifier
                                    .size(250.dp)
                            )
                            HeightSpacer(36)
                            Text(
                                text = "Bank account balance fetch failed.\n" + viewModel.getMessage(),
                                style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}