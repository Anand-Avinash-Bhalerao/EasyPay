package com.billion_dollor_company.easypay.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.models.RecentInteractionInfo
import com.billion_dollor_company.easypay.ui.components.CommonScaffold
import com.billion_dollor_company.easypay.ui.components.HeightSpacer
import com.billion_dollor_company.easypay.ui.components.IPAddressChangeDialog
import com.billion_dollor_company.easypay.ui.home.components.BottomSection
import com.billion_dollor_company.easypay.ui.home.components.CompanyLogo
import com.billion_dollor_company.easypay.ui.home.components.CreditCard
import com.billion_dollor_company.easypay.ui.home.components.MoreSection
import com.billion_dollor_company.easypay.ui.home.components.PaymentButtonsRow
import com.billion_dollor_company.easypay.ui.home.components.ReceiveMoneySection
import com.billion_dollor_company.easypay.ui.home.components.RecentSection
import com.billion_dollor_company.easypay.ui.home.components.UserNameSection
import com.billion_dollor_company.easypay.util.Constants

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    goScanQR: () -> Unit,
    onUserClicked: (RecentInteractionInfo) -> Unit,
    onCheckBalanceClicked : () -> Unit,
    onSelfQRClick : () -> Unit
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val pspIpAddress = viewModel.getIPAddress().collectAsState()

    CommonScaffold(
        hasFAB = true,
        iconFAB = Icons.Default.QrCodeScanner,
        onFABClick = goScanQR
    ) {
        CompanyLogo()
        HeightSpacer()

        UserNameSection()
        HeightSpacer()

        CreditCard(
            name = Constants.PayerDetails.CARD_NAME,
            cardNumber = "2342543754367564",
            companyLogo = R.drawable.visa_svg,
            expiryDate = "09/24"
        )
        HeightSpacer()

        PaymentButtonsRow(
            goScanQR,
            onCheckBalanceClicked
        )
        HeightSpacer()


//        UPILiteSection()
//        HeightSpacer()

        RecentSection(
            list = viewModel.dummyRecentList,
            onUserClicked = onUserClicked
        )
        HeightSpacer()

        ReceiveMoneySection(onSelfQRClick)
        HeightSpacer()

        var isDialogVisible by remember {
            mutableStateOf(false)
        }
        MoreSection(
            changeIPClick = {
                isDialogVisible = !isDialogVisible
            }
        )
        if (isDialogVisible) {
            IPAddressChangeDialog(
                currentIPAddress = pspIpAddress.value,
                saveClicked = { newIP ->
                    viewModel.saveIPAddress(newIP)
                    isDialogVisible = false
                },
                cancelClicked = {
                    isDialogVisible = false
                }
            )

        }
        HeightSpacer()

        Divider()

        BottomSection()

    }
}

