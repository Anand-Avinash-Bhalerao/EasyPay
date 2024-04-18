package com.billion_dollor_company.easypay.ui.transactionComplete

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.ui.amount.PayeeDetailsSections
import com.billion_dollor_company.easypay.ui.components.HeightSpacer
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.Helper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun TransactionCompleteScreen(
    onBackPress: () -> Unit
) {

    Surface {

        val viewModel: TransactionCompleteViewModel = hiltViewModel()
        var transactionStatus by remember {
            mutableStateOf(viewModel.getTransactionStatus())
        }

        LaunchedEffect(Unit) {
            viewModel.viewModelScope.launch {
                delay(1500)
                viewModel.startTransaction()
                transactionStatus = viewModel.getTransactionStatus()
            }
        }

        val loadingComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_loading))
        val successComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_success))
        val failureComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_fail))

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (backIcon, transactionDetails, animationRef, transactionMessage) = createRefs()

            if (viewModel.getTransactionStatus() != Constants.Values.LOADING)
                IconButton(
                    onClick = { onBackPress() },
                    modifier = Modifier
                        .padding(top = 28.dp, start = 12.dp)
                        .constrainAs(backIcon) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        Modifier.size(30.dp)
                    )
                }


            TransactionDetailsSections(
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .constrainAs(transactionDetails) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            if (transactionStatus == Constants.Values.LOADING) {
                LottieAnimation(
                    composition = loadingComposition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .size(350.dp)
                        .constrainAs(animationRef) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(transactionDetails.bottom)
                            bottom.linkTo(transactionMessage.top)
                        }
                )
            } else {
                val composition =
                    if (transactionStatus == Constants.Values.SUCCESS) successComposition else failureComposition
                LottieAnimation(
                    composition = composition,
                    iterations = 1,
                    modifier = Modifier
                        .size(300.dp)
                        .constrainAs(animationRef) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(transactionDetails.bottom)
                            bottom.linkTo(transactionMessage.top)
                        }
                )
            }
            Text(
                text =
                if (transactionStatus == Constants.Values.LOADING)
                    "Processing Transaction..\nPlease don't press back or close the app!"
                else if (transactionStatus == Constants.Values.SUCCESS) viewModel.getTransactionMessage()
                else "Payment Failed!\n${viewModel.getTransactionMessage()}",
                style =
                if (transactionStatus == Constants.Values.LOADING)
                    MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    )
                else MaterialTheme.typography.titleLarge.copy(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 36.dp)
                    .constrainAs(transactionMessage) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

@Composable
fun TransactionDetailsSections(
    viewModel: TransactionCompleteViewModel,
    modifier: Modifier = Modifier
) {

    val fullName = viewModel.getPayeeName()
    val imageId =
        if (fullName.lowercase(Locale.getDefault()).contains("akshay")) {
            R.drawable.male1
        } else if (fullName.lowercase(Locale.getDefault()).contains("abhishek")) {
            R.drawable.male3
        } else if (fullName.lowercase(Locale.getDefault()).contains("shruti")) {
            R.drawable.female1
        } else if (fullName.lowercase(Locale.getDefault()).contains("rucha")) {
            R.drawable.female4
        } else {
            R.drawable.male2
        }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Paying to:",
            style = MaterialTheme.typography.titleMedium
        )
        HeightSpacer(18)

        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Payee image",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        HeightSpacer(18)
        Text(
            text = viewModel.getPayeeName(),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = viewModel.getPayeeUpiID(),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        HeightSpacer(12)
        Text(
            text = "Paying amount:",
            style = MaterialTheme.typography.titleMedium
        )
        HeightSpacer(4)
        Text(
            text = "₹ " + Helper.getFormattedAmount(viewModel.getTransferAmount()),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

