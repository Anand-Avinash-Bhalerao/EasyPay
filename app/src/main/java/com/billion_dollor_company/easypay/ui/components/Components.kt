package com.billion_dollor_company.easypay.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeightSpacer(height: Int = 24) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun WidthSpacer(width: Int = 12) {
    Spacer(modifier = Modifier.width(width.dp))
}