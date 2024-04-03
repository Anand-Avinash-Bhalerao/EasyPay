package com.billion_dollor_company.easypay.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(
                goScanQR = {
                    navController.navigate(Screen.ScanScreen.route)
//                    navController.navigate(route = Screen.PinEnterScreen.route)
//                    navController.navigate(route = Screen.PinEnterScreen.route)
                },
                onUserClicked = { info ->
                    var fullName = info.firstName + " "
                    if (info.middleName.isNotEmpty()) fullName += info.middleName + " "
                    fullName += info.lastName
                    navController.navigate(
                        Screen.AmountEnterScreen.route
                                + "/$fullName"
                                + "/${info.mobileNo}"
                                + "/${info.upiID}"
                                + "/${info.userImage}"
                    )
                },
                onCheckBalanceClicked = {
                    navController.navigate(
                        Screen.CheckBalancePinEnterScreen.route
                    )
                },
                onSelfQRClick = {
                    navController.navigate(
                        Screen.SelfQRScreen.route
                    )
                }
            )
        }

        composable(
            route = Screen.ScanScreen.route
        ) {
            ScanScreen(
                onScanned = { payeeInfo: PayeeInfo ->
                    navController.navigate(
                        Screen.AmountEnterScreen.route
                                + "/${payeeInfo.name}"
                                + "/${payeeInfo.phoneNo}"
                                + "/${payeeInfo.upiID}"
                                + "/${payeeInfo.imageID}"
                    )
                },
                onCanceled = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = Screen.AmountEnterScreen.route + "/{${Constants.PayeeInfo.FULL_NAME}}/{${Constants.PayeeInfo.PHONE_NO}}/{${Constants.PayeeInfo.UPI_ID}}/{${Constants.PayeeInfo.IMAGE_ID}}",
            arguments = listOf(
                navArgument(Constants.PayeeInfo.FULL_NAME) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayeeInfo.PHONE_NO) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayeeInfo.UPI_ID) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayeeInfo.IMAGE_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            AmountEnterScreen(
                onBackClick = {
                    navController.navigate(route = Screen.HomeScreen.route)
                },
                onPayClick = { pinCaptureReqInfo ->
                    navController.navigate(
                        route = Screen.TransactionPinEnterScreen.route
                                + "/${pinCaptureReqInfo.payeeFullName}"
                                + "/${pinCaptureReqInfo.payeeUpiID}"
                                + "/${pinCaptureReqInfo.payerUpiID}"
                                + "/${pinCaptureReqInfo.accountNo}"
                                + "/${pinCaptureReqInfo.bankName}"
                                + "/${pinCaptureReqInfo.amountToTransfer}"
                    )
                }
            )
        }
        composable(
            route = Screen.TransactionPinEnterScreen.route +
                    "/{${Constants.PayeeInfo.FULL_NAME}}" +
                    "/{${Constants.PayeeInfo.UPI_ID}}" +
                    "/{${Constants.PayerInfo.UPI_ID}}" +
                    "/{${Constants.PayerInfo.ACCOUNT_NO}}" +
                    "/{${Constants.PayerInfo.BANK_NAME}}" +
                    "/{${Constants.Values.AMOUNT}}",
            arguments = listOf(
                navArgument(Constants.PayeeInfo.FULL_NAME) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayeeInfo.UPI_ID) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayerInfo.BANK_NAME) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayerInfo.ACCOUNT_NO) {
                    type = NavType.StringType
                },
                navArgument(Constants.Values.AMOUNT) {
                    type = NavType.StringType
                }
            )
        ) {
            PinCaptureScreen { transactionInfo ->

                // since the encrypted password could contain / we need to encode it
                val encodedPassword =
                    Helper.encodeSpecialCharString(transactionInfo.encryptedPassword)

                navController.navigate(
                    route = Screen.TransactionCompleteScreen.route
                            + "/${transactionInfo.payeeUpiID}"
                            + "/${transactionInfo.payeeFullName}"
                            + "/${transactionInfo.payerUpiID}"
                            + "/${transactionInfo.payerBankName}"
                            + "/${transactionInfo.payerAccountNo}"
                            + "/${encodedPassword}"
                            + "/${transactionInfo.amountToTransfer}"
                )
            }
        }

        composable(
            route = Screen.TransactionCompleteScreen.route +
                    "/{${Constants.PayeeInfo.UPI_ID}}" +
                    "/{${Constants.PayeeInfo.FULL_NAME}}" +
                    "/{${Constants.PayerInfo.UPI_ID}}" +
                    "/{${Constants.PayerInfo.BANK_NAME}}" +
                    "/{${Constants.PayerInfo.ACCOUNT_NO}}" +
                    "/{${Constants.Values.ENCRYPTED_PASSWORD}}" +
                    "/{${Constants.Values.AMOUNT}}",
            arguments = listOf(

                navArgument(Constants.PayeeInfo.UPI_ID) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayeeInfo.FULL_NAME) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayerInfo.UPI_ID) {
                    type = NavType.StringType
                },

                navArgument(Constants.PayerInfo.BANK_NAME) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayerInfo.ACCOUNT_NO) {
                    type = NavType.StringType
                },

                navArgument(Constants.Values.ENCRYPTED_PASSWORD) {
                    type = NavType.StringType
                },

                navArgument(Constants.Values.AMOUNT) {
                    type = NavType.StringType
                }
            )
        ) {
            TransactionCompleteScreen(
                onBackPress = {
                    navController.navigateUp() // goes to pin screen
                    navController.navigateUp() // goes to amount enter
                    navController.navigateUp() // goes to home page
                }
            )
        }

        composable(
            route = Screen.CheckBalancePinEnterScreen.route

        ) {
            CheckBalanceScreen { checkBalanceInfo ->

                // since the encrypted password could contain / we need to encode it
                val encodedPassword =
                    Helper.encodeSpecialCharString(checkBalanceInfo.encryptedPassword)

                navController.navigate(
                    route = Screen.CheckBalanceCompleteScreen.route
                            + "/${checkBalanceInfo.upiID}"
                            + "/${encodedPassword}"
                )
            }
        }
        composable(
            route = Screen.CheckBalanceCompleteScreen.route +
                    "/{${Constants.UserInfo.UPI_ID}}" +
                    "/{${Constants.UserInfo.ENCRYPTED_PASSWORD}}"
        ) {
            CheckBalanceCompleteScreen(
                onBackClick = {
                    navController.navigateUp()
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = Screen.SelfQRScreen.route
        ) {
            SelfQRScreen(
                onBackClick = {
                    navController.navigateUp()
                    navController.navigateUp()
                }
            )
        }

    }
}