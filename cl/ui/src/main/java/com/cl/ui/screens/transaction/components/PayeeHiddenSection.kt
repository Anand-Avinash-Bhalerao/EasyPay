package com.cl.ui.screens.transaction.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.cl.ui.theme.light_grey

@Composable
fun PayeeHiddenSection(
    payeeName: String,
    amount: String,
    refID: String,
    bankName: String,
    accountNo: String,
    modifier: Modifier
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .alpha(1f),
        colors = CardDefaults.cardColors(containerColor = light_grey)
    ){
        Surface {


        }
    }
}