package com.billion_dollor_company.easypay.ui.transaction.transactionRequestor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionCLReqScreen(
    isPasswordFetched: Boolean,
    navigateToCL: () -> Unit,
    navigateToCompleteScreen: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(1000)
        if (!isPasswordFetched) {
            navigateToCL()
        } else {
            navigateToCompleteScreen()
        }
    }

    Scaffold(
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = {
                        Text(text = "Initializing PIN Capture")
                    }
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
        ) {

        }
    }

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

}