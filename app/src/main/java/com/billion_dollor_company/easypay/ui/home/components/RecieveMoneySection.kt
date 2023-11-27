package com.billion_dollor_company.easypay.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.ui.components.HeightSpacer
import com.billion_dollor_company.easypay.ui.components.WidthSpacer


@Composable
fun ReceiveMoneySection() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.tertiaryContainer
//        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp
                )

        ) {
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
            ) {
                Text(
                    text = "Receive Money"
                )
                HeightSpacer(2)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCode,
                        contentDescription = "User's QR Code icon",
                        modifier = Modifier
                            .size(16.dp)
                    )
                    WidthSpacer(6)
                    Text(
                        text = "UPI ID :anandbhalerao@abc",
                        fontWeight = FontWeight.Thin
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.NavigateNext,
                contentDescription = "Receive money go logo",
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
            )
        }
    }
}
