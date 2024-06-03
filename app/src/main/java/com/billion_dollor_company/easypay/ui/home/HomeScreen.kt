package com.billion_dollor_company.easypay.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.billion_dollor_company.easypay.ui.home.components.BottomSection
import com.billion_dollor_company.easypay.ui.home.components.CompanyLogo
import com.billion_dollor_company.easypay.ui.home.components.CreditCard
import com.billion_dollor_company.easypay.ui.home.components.IPAddressChangeDialog
import com.billion_dollor_company.easypay.ui.home.components.MoreSection
import com.billion_dollor_company.easypay.ui.home.components.PaymentButtonsRow
import com.billion_dollor_company.easypay.ui.home.components.ReceiveMoneySection
import com.billion_dollor_company.easypay.ui.home.components.RecentSection
import com.billion_dollor_company.easypay.ui.home.components.NetworkDialog
import com.billion_dollor_company.easypay.ui.home.components.UserNameSection
import com.billion_dollor_company.easypay.util.Constants
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    goScanQR: () -> Unit,
    onUserClicked: (RecentInteractionInfo) -> Unit,
    onCheckBalanceClicked: () -> Unit,
    onSelfQRClick: () -> Unit
) {


    val viewModel: HomeViewModel = hiltViewModel()
    val pspIpAddress = viewModel.getIPAddress().collectAsState()


    var isRegisterDialogVisible by remember {
        mutableStateOf(false)
    }

    var isFetchKeysDialogVisible by remember {
        mutableStateOf(false)
    }

    if(!viewModel.isKeyStored()){
        isFetchKeysDialogVisible = true
    }

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

        var isIPDialogVisible by remember {
            mutableStateOf(false)
        }



        MoreSection(
            changeIPClick = {
                isIPDialogVisible = !isIPDialogVisible
            },
            registerClick = {
                isRegisterDialogVisible = !isRegisterDialogVisible
            },
            fetchKeysClick = {
                isFetchKeysDialogVisible = !isFetchKeysDialogVisible
            }
        )

        HeightSpacer()

        HorizontalDivider()

        BottomSection()

        if (isIPDialogVisible) {
            IPAddressChangeDialog(
                currentIPAddress = pspIpAddress.value,
                saveClicked = { newIP ->
                    viewModel.saveIPAddress(newIP)
                    isIPDialogVisible = false
                },
                cancelClicked = {
                    isIPDialogVisible = false
                }
            )

        }


        //this dialog is for cl registration.
        if (isRegisterDialogVisible) {
            var isLoading by remember {
                mutableStateOf(true)
            }
            var wasSuccessful by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(Unit) {
                Log.d("TAGGGG", "Registering")
                wasSuccessful = viewModel.register()
                delay(1000)
                isLoading = false
                delay(4000)
                isRegisterDialogVisible = false
            }

            NetworkDialog(
                isLoading = isLoading,
                wasSuccessful = wasSuccessful,
                isLoadingText = "Registering CL",
                successfulText = "CL Registered Successfully",
                failedText = "CL Registration Failed",
                closeDialog = {
                    isRegisterDialogVisible = false
                }
            )
        }

        // this dialog is for fetching the npci keys.
        if (isFetchKeysDialogVisible) {
            var isLoading by remember {
                mutableStateOf(true)
            }
            var wasSuccessful by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(Unit) {
                Log.d("TAGGGG", "Fetching keys")
                wasSuccessful = viewModel.getKeys()
                delay(1000)
                isLoading = false
                delay(4000)
                isFetchKeysDialogVisible = false
            }

            NetworkDialog(
                isLoading = isLoading,
                wasSuccessful = wasSuccessful,
                isLoadingText = "Fetching NPCI Keys",
                successfulText = "Fetched keys successfully!",
                failedText = "Fetching keys failed",
                closeDialog = {
                    isFetchKeysDialogVisible = false
                }
            )
        }

    }
}

