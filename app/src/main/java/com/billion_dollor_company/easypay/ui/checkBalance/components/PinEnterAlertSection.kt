package com.billion_dollor_company.easypay.ui.checkBalance.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.ui.theme.pin_enter_alert_bg_color
import com.billion_dollor_company.easypay.ui.theme.pin_enter_alert_content_color


@Composable
fun PinEnterAlertSection(
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = pin_enter_alert_bg_color)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Paying alert icon",
                modifier = Modifier
                    .size(36.dp)
                    .align(alignment = Alignment.CenterVertically),
                tint = pin_enter_alert_content_color
            )
            Spacer(modifier = Modifier.width(18.dp))

            Text(
                text = "UPI PIN will keep your account secure from unauthorized access. Do not share this PIN with anyone.",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

