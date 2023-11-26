package com.billion_dollor_company.easypay.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.ui.theme.seed


@Composable
fun CreditCard(
    name: String,
    cardNumber: String,
    companyLogo: Int,
    expiryDate: String
) {
    val horizontalPadding = 24.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = seed,
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(
            width = 1.dp,
            color = Color.DarkGray
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = horizontalPadding, vertical = 20.dp)

        ) {
            Image(
                painter = painterResource(id = companyLogo),
                contentDescription = "Card company logo",
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
            )
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
            ) {
                val firstHalf = "${cardNumber.subSequence(0, 4)} ${cardNumber.subSequence(4, 8)}"
                val secondHalf =
                    "${cardNumber.subSequence(8, 12)} ${cardNumber.subSequence(12, 16)}"
                Text(
                    text = firstHalf,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                )
                Text(
                    text = secondHalf,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.dark_pattern2),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .padding(horizontalPadding)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = expiryDate,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White
                    )
                )
            }

            Column(
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .padding(horizontalPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CVV",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "***",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White
                    )
                )
            }
        }
    }
}
