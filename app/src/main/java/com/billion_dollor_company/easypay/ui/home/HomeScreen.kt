package com.billion_dollor_company.easypay.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.ui.components.CommonScaffold

@Composable
fun HomeScreen() {
    CommonScaffold(
        hasFAB = true,
        iconFAB = Icons.Default.QrCodeScanner,
    ) {
        CompanyLogo()
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        UserNameSection()
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

@Composable
fun UserNameSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
        ) {
            Text(
                text = "Hello Anand!",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "We were waiting for you",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Thin
                )
            )
        }
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(1.dp)
                .align(alignment = Alignment.CenterEnd),
            shape = CircleShape,
            border = BorderStroke(1.5.dp, Color.DarkGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sample_dp),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
            )
        }
    }
}