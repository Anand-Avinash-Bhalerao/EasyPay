package com.billion_dollor_company.easypay.ui.pin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.ui.pin.PinCaptureViewModel


@Composable
fun EnteredPinSection(
    modifier: Modifier,
    viewModel: PinCaptureViewModel
) {
    val pinEntered = viewModel.getPin()
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        repeat(6) { index ->
            val isCellFilled = when {
                index >= pinEntered.length -> false
                else -> true
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                ) {
                    if (isCellFilled) {
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = "Pin filled",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(16.dp)
                                .align(Alignment.Center)
                        )
                    } else {
                        val barColor = if (index == pinEntered.length) Color.Black else Color.LightGray
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(2.dp)
                                .background(color = barColor)
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }
    }
}

