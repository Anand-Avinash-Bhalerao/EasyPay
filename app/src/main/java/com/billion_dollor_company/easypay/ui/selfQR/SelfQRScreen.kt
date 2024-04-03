package com.billion_dollor_company.easypay.ui.selfQR

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.billion_dollor_company.easypay.ui.components.HeightSpacer
import com.billion_dollor_company.easypay.ui.selfQR.components.AlertSection
import com.billion_dollor_company.easypay.util.Constants
import com.lightspark.composeqr.DotShape
import com.lightspark.composeqr.QrCodeColors
import com.lightspark.composeqr.QrCodeView

@Composable
fun SelfQRScreen(
    onBackClick: () -> Unit
) {
    val viewModel: SelfQRViewModel = hiltViewModel()
    Surface {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {


            val (backIcon, qr, heading, infoSection, alertSection) = createRefs()

            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier
                    .padding(top = 28.dp, start = 12.dp)
                    .constrainAs(backIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    Modifier.size(30.dp)
                )
            }

            Text(
                text = "Your QR Code",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 36.dp)
                    .constrainAs(heading) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            )

            AlertSection(modifier = Modifier
                .constrainAs(alertSection) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(heading.bottom, 16.dp)
                }
            )

            Column(
                modifier = Modifier
                    .constrainAs(infoSection) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(alertSection.bottom, 24.dp)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = (Constants.PayerDetails.FIRST_NAME + " " + Constants.PayerDetails.LAST_NAME).uppercase(),
                    style = MaterialTheme.typography.titleLarge
                )
                HeightSpacer(6)
                Text(
                    text = Constants.PayerDetails.UPI_ID,
                    style = MaterialTheme.typography.titleLarge
                )
                HeightSpacer(6)
                Text(
                    text = Constants.PayerDetails.PHONE_NO,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Column(
                modifier = Modifier
                    .constrainAs(qr) {
                        top.linkTo(infoSection.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GetQRCode()
            }
        }
    }
}


@Composable
fun GetQRCode() {
    val data =
        "https://upi://pay?name=" + Constants.PayerDetails.FIRST_NAME + "_" + Constants.PayerDetails.LAST_NAME + "&upiID=" + Constants.PayerDetails.UPI_ID + "&phoneNo=" + Constants.PayerDetails.PHONE_NO;
    QrCodeView(
        data = data,
        modifier = Modifier.size(300.dp),
        colors = QrCodeColors(
            background = MaterialTheme.colorScheme.surface,
            foreground = Color.White
        ),
        dotShape = DotShape.Circle
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            BasicText(
                text = "₹",
                style = TextStyle.Default.copy(
                    color = Color.White,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif
                )
            )
        }
    }
}