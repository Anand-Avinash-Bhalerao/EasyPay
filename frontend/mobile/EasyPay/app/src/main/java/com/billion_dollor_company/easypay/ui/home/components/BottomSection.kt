package com.billion_dollor_company.easypay.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.ui.components.HeightSpacer

@Composable
fun BottomSection() {
    HeightSpacer(12)
    Image(
        painter = painterResource(id = R.drawable.upi_paying_image),
        contentDescription = "UPI payment illustration",
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
    HeightSpacer(12)
    Text(
        text = "Pay easily with EasyPay",
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            textAlign = TextAlign.Center
        )
    )
    HeightSpacer(24)
}