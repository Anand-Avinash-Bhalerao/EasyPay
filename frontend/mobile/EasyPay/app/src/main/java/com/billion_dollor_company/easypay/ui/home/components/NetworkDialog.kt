package com.billion_dollor_company.easypay.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.billion_dollor_company.easypay.R

@Composable
fun NetworkDialog(
    isLoading: Boolean,
    wasSuccessful: Boolean,
    closeDialog : () -> Unit,
    isLoadingText : String,
    successfulText : String,
    failedText : String
) {
    val successComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_success))
    val failureComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_fail))

    Dialog(
        onDismissRequest = {
            if(!isLoading) closeDialog()
        }
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .padding(24.dp)
                        .size(62.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                val composition =
                    if (wasSuccessful) successComposition else failureComposition
                LottieAnimation(
                    composition = composition,
                    iterations = 1,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .size(110.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Text(
                text =
                if (isLoading)
                    isLoadingText
                else {
                    if (wasSuccessful)
                        successfulText
                    else
                        failedText
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp, bottom = 36.dp),
                style = MaterialTheme.typography.titleLarge
            )

        }
    }
}