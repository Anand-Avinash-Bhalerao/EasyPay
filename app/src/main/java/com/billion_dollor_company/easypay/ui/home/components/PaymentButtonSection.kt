package com.billion_dollor_company.easypay.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.SendToMobile
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.billion_dollor_company.easypay.ui.components.HeightSpacer


@Composable
fun PaymentButtonsRow(
    goScanQR: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Top
        ) {
            PaymentButton(
                imageVector = Icons.Outlined.QrCodeScanner,
                text = "Scan any QR code",
                onClick = goScanQR,
            )
            PaymentButton(
                imageVector = Icons.Outlined.Contacts,
                text = "Pay contacts",
            )
            PaymentButton(
                imageVector = Icons.Default.SendToMobile,
                text = "Pay number",
            )

            PaymentButton(
                imageVector = Icons.Default.AlternateEmail,
                text = "Pay UPI ID",
            )
        }
    }
}

@Composable
fun PaymentButton(
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .width(78.dp)
            .clickable {
                onClick()
            }
            .padding(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = text,
//            tint = MaterialTheme.colorScheme.primary
        )
        HeightSpacer(height = 6)
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(horizontal = 6.dp)
        )
    }
}
