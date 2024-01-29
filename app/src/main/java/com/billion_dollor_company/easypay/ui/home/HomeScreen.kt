package com.billion_dollor_company.easypay.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.models.PayeeInfo
import com.billion_dollor_company.easypay.ui.components.CommonScaffold
import com.billion_dollor_company.easypay.ui.components.HeightSpacer
import com.billion_dollor_company.easypay.ui.home.components.CompanyLogo
import com.billion_dollor_company.easypay.ui.home.components.CreditCard
import com.billion_dollor_company.easypay.ui.home.components.MoreSection
import com.billion_dollor_company.easypay.ui.home.components.PaymentButtonsRow
import com.billion_dollor_company.easypay.ui.home.components.ReceiveMoneySection
import com.billion_dollor_company.easypay.ui.home.components.RecentSection
import com.billion_dollor_company.easypay.ui.home.components.UserNameSection

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    goScanQR: () -> Unit,
    onUserClicked: () -> Unit
) {

    val viewModel: HomeViewModel = hiltViewModel()

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
            name = "Anand Bhalerao",
            cardNumber = "2342543754367564",
            companyLogo = R.drawable.visa_svg,
            expiryDate = "09/24"
        )
        HeightSpacer()

        PaymentButtonsRow(goScanQR)
        HeightSpacer()


//        UPILiteSection()
//        HeightSpacer()

        RecentSection(
            list = viewModel.dummyRecentList,
            onUserClicked = onUserClicked
        )
        HeightSpacer()

        ReceiveMoneySection()
        HeightSpacer()


        MoreSection()
        HeightSpacer()

    }
}

