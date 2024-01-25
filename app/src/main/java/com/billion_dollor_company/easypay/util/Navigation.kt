package com.billion_dollor_company.easypay.util

import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.activity
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
                                + "/${payeeInfo.upiID}/"
                                + "${payeeInfo.imageID}"
                    )
                },
                onCanceled = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = Screen.AmountEnterScreen.route + "/{${Constants.NAME}}/{${Constants.PHONE_NO}}/{${Constants.UPI_ID}}/{${Constants.IMAGE_ID}}",
            arguments = listOf(
                navArgument(Constants.NAME) {
                    type = NavType.StringType
                },
                navArgument(Constants.PHONE_NO) {
                    type = NavType.StringType
                },
                navArgument(Constants.UPI_ID) {
                    type = NavType.StringType
                },
                navArgument(Constants.IMAGE_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            AmountEnterScreen(
                onBackClick = {
                    navController.navigate(route = Screen.HomeScreen.route)
                },
                onPayClick = {
                    navController.navigate(route = Screen.PinEnterScreen.route)
                }
            )
        }
        composable(
            route = Screen.PinEnterScreen.route
        ) {
            PinCaptureScreen(

            )
        }


    }
}