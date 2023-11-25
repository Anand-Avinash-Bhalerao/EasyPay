package com.billion_dollor_company.easypay.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.ui.components.CommonScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    CommonScaffold(
        hasFAB = true,
        iconFAB = Icons.Default.QrCodeScanner,
    ) {
        CompanyLogo()
    }
}

@Composable
fun CompanyLogo() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.PriceChange,
            contentDescription = "Money Logo",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(36.dp)
        )
        Text(
            text = "EasyPay",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier
                .padding(start = 12.dp)
        )
    }
}