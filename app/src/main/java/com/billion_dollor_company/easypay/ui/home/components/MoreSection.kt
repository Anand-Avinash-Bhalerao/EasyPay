package com.billion_dollor_company.easypay.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.ui.components.WidthSpacer


@Composable
fun MoreSection(
    checkBalanceClick : () -> Unit = {},
    changeIPClick : () -> Unit = {},
    seeHistoryClick : () -> Unit = {},
    contactUsClick : () -> Unit = {}
) {
//    MoreItem(
//        title = "Check Balance",
//        icon = Icons.Default.AccountBalanceWallet,
//        onClick = checkBalanceClick
//    )

    MoreItem(
        title = "PSP IP Address",
        icon = Icons.Default.AdminPanelSettings,
        onClick = changeIPClick
    )

//    MoreItem(
//        title = "See transaction history",
//        icon = Icons.Default.History,
//        onClick = seeHistoryClick
//    )
//    MoreItem(
//        title = "Contact us",
//        icon = Icons.Default.Phone,
//        onClick = contactUsClick
//    )
}

@Composable
fun MoreItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .align(alignment = Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$title Icon",
                tint = MaterialTheme.colorScheme.primary
            )
            WidthSpacer()
            Text(text = title)
        }

        Icon(
            imageVector = Icons.Default.NavigateNext,
            contentDescription = "$title go icon",
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
        )
    }
}