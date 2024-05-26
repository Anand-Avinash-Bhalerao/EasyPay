package com.billion_dollor_company.easypay.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.billion_dollor_company.easypay.models.PayeeInfo
import com.billion_dollor_company.easypay.ui.amount.AmountEnterScreen
import com.billion_dollor_company.easypay.ui.checkBalance.CheckBalanceScreen
import com.billion_dollor_company.easypay.ui.checkBalanceComplete.CheckBalanceCompleteScreen
import com.billion_dollor_company.easypay.ui.home.HomeScreen
import com.billion_dollor_company.easypay.ui.pin.PinCaptureScreen
import com.billion_dollor_company.easypay.ui.scan.ScanScreen
import com.billion_dollor_company.easypay.ui.selfQR.SelfQRScreen
import com.billion_dollor_company.easypay.ui.transactionComplete.TransactionCompleteScreen

@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen
    ) {
        composable<Screen.HomeScreen> {
            HomeScreen(
                goScanQR = {
                    navController.navigate(Screen.ScanScreen)
                },
                onUserClicked = { info ->
                    var fullName = info.firstName + " "
                    if (info.middleName.isNotEmpty()) fullName += info.middleName + " "
                    fullName += info.lastName
                    navController.navigate(
                        Screen.AmountEnterScreen(
                            fullName = fullName,
                            phoneNo = info.mobileNo,
                            upiID = info.upiID,
                            imageID = info.userImage
                        )
                    )
                },
                onCheckBalanceClicked = {
                    navController.navigate(
                        Screen.CheckBalancePinEnterScreen
                    )
                },
                onSelfQRClick = {
                    navController.navigate(
                        Screen.SelfQRScreen
                    )
                }
            )
        }

        composable<Screen.ScanScreen> {
            ScanScreen(
                onScanned = { payeeInfo: PayeeInfo ->
                    navController.navigate(
                        Screen.AmountEnterScreen(
                            fullName = payeeInfo.name,
                            phoneNo = payeeInfo.phoneNo,
                            upiID = payeeInfo.upiID,
                            imageID = payeeInfo.imageID
                        )
                    )
                },
                onCanceled = {
                    navController.navigateUp()
                }
            )
        }

        composable<Screen.AmountEnterScreen> {
            val passedData = it.toRoute<Screen.AmountEnterScreen>()
            AmountEnterScreen(
                passedData,
                onBackClick = {
                    navController.navigate(route = Screen.HomeScreen)
                },
                onPayClick = { pinCaptureReqInfo ->
                    navController.navigate(
                        Screen.TransactionPinEnterScreen(
                            payeeFullName = pinCaptureReqInfo.payeeFullName,
                            payeeUpiID = pinCaptureReqInfo.payeeUpiID,
                            payerUpiID = pinCaptureReqInfo.payerUpiID,
                            payerAccountNumber = pinCaptureReqInfo.accountNo,
                            payerBankName = pinCaptureReqInfo.bankName,
                            amountToPay = pinCaptureReqInfo.amountToTransfer
                        )
                    )
                }
            )
        }
        composable<Screen.TransactionPinEnterScreen> {
            val passedData = it.toRoute<Screen.TransactionPinEnterScreen>()

            PinCaptureScreen(
                passedData,
                onSubmitClick = { transactionInfo ->
                    // since the encrypted password could contain / we need to encode it

                    navController.navigate(
                        Screen.TransactionCompleteScreen(
                            payeeFullName = transactionInfo.payeeFullName,
                            payeeUpiID = transactionInfo.payeeUpiID,
                            payerUpiID = transactionInfo.payerUpiID,
                            payerBankName = transactionInfo.payerBankName,
                            payerAccountNumber = transactionInfo.payerAccountNo,
                            encryptedPassword = transactionInfo.encryptedPassword,
                            amount = transactionInfo.amountToTransfer
                        )
                    )
                }
            )
        }

        composable<Screen.TransactionCompleteScreen> {
            val passedData = it.toRoute<Screen.TransactionCompleteScreen>()
            TransactionCompleteScreen(
                passedData,
                onBackPress = {
                    navController.navigateUp() // goes to pin screen
                    navController.navigateUp() // goes to amount enter
                    navController.navigateUp() // goes to home page
                }
            )
        }

        composable<Screen.CheckBalancePinEnterScreen> {
            CheckBalanceScreen { checkBalanceInfo ->

                // since the encrypted password could contain / we need to encode it
                val encodedPassword =
                    Helper.encodeSpecialCharString(checkBalanceInfo.encryptedPassword)

                navController.navigate(

                    Screen.CheckBalanceCompleteScreen(
                        upiID = checkBalanceInfo.upiID,
                        encryptedPassword = encodedPassword
                    )
                )
            }
        }

        composable<Screen.CheckBalanceCompleteScreen> {

            val passedData = it.toRoute<Screen.CheckBalanceCompleteScreen>()

            CheckBalanceCompleteScreen(
                passedData,
                onBackClick = {
                    navController.navigateUp()
                    navController.navigateUp()
                }
            )
        }

        composable<Screen.SelfQRScreen> {
            SelfQRScreen(
                onBackClick = {
                    navController.navigateUp()
                    navController.navigateUp()
                }
            )
        }

    }
}