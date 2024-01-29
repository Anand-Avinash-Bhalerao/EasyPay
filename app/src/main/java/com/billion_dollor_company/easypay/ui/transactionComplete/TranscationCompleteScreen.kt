package com.billion_dollor_company.easypay.ui.transactionComplete

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.ui.pin.PinCaptureViewModel
import com.billion_dollor_company.easypay.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TransactionCompleteScreen(

) {

    val viewModel: TransactionCompleteViewModel = hiltViewModel()
    var transactionStatus by remember {
        mutableStateOf(viewModel.getTransactionStatus())
    }

    LaunchedEffect(Unit) {
        delay(1500)
        viewModel.viewModelScope.launch {
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
        val (animationRef) = createRefs()
        LottieAnimation(
            composition =
            if (transactionStatus == Constants.Values.LOADING)
                loadingComposition
            else if (transactionStatus == Constants.Values.SUCCESS)
                successComposition
            else failureComposition,

            iterations =
            if (transactionStatus == Constants.Values.LOADING)
                LottieConstants.IterateForever
            else 1,
            modifier = Modifier
                .size(300.dp)
                .constrainAs(animationRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }

}