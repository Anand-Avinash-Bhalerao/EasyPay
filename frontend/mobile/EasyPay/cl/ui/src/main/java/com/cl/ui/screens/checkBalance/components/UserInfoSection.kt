package com.cl.ui.screens.checkBalance.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cl.ui.R


// This section is at the top of the Pin enter screen. It contains payer bank and acc number.
@Composable
fun UserInfoSection(
    bankName: String,
    accountNo: String,
    modifier: Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        val (bankNameSection, accountNoSection, logoSection) = createRefs()

        Text(
            text = bankName,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.Black,
            ),
            modifier = Modifier.constrainAs(bankNameSection) {
                top.linkTo(parent.top)
                bottom.linkTo(accountNoSection.top)
                start.linkTo(parent.start)
            }
        )

        Text(
            text = accountNo,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.Black,
            ),
            modifier = Modifier.constrainAs(accountNoSection) {
                top.linkTo(bankNameSection.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.upi_logo),
            contentDescription = "UPI logo",
            modifier = Modifier
                .height(30.dp)
                .constrainAs(logoSection) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}
