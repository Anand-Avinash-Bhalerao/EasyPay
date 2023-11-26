package com.billion_dollor_company.easypay.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.R


@Composable
fun UPILiteSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.upi_lite_logo),
                    contentDescription = "UPI lite logo",
                    modifier = Modifier
                        .width(50.dp),
                    contentScale = ContentScale.FillWidth
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Text(
                    text = "PIN-less Payments"
                )
            }
            Button(
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd),
                onClick = {},
            ) {
                Text(text = "TRY NOW")
            }
        }
    }
}

