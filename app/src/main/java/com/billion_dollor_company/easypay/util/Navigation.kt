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
import com.billion_dollor_company.easypay.ui.home.HomeScreen
import com.billion_dollor_company.easypay.ui.pin.PinCaptureScreen
import com.billion_dollor_company.easypay.ui.scan.ScanScreen

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
                onPayClick = { transactionInfo ->
                    navController.navigate(
                        route = Screen.PinEnterScreen.route
                                + "/${transactionInfo.payeeFullName}"
                                + "/${transactionInfo.payeeUpiID}"
                                + "/${transactionInfo.payeeBankName}"
                                + "/${transactionInfo.payeeAccountNo}"
                                + "/${transactionInfo.amountToTransfer}"
                    )
                }
            )
        }
        composable(
            route = Screen.PinEnterScreen.route + "/{${Constants.PayeeInfo.FULL_NAME}}/{${Constants.PayeeInfo.UPI_ID}}/{${Constants.PayeeInfo.BANK_NAME}}/{${Constants.PayeeInfo.ACCOUNT_NO}}/{${Constants.PayeeInfo.AMOUNT}}",
            arguments = listOf(
                navArgument(Constants.PayeeInfo.FULL_NAME) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayeeInfo.UPI_ID) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayeeInfo.BANK_NAME) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayeeInfo.ACCOUNT_NO) {
                    type = NavType.StringType
                },
                navArgument(Constants.PayeeInfo.AMOUNT) {
                    type = NavType.StringType
                }
            )
        ) {
            PinCaptureScreen(

            )
        }


    }
}