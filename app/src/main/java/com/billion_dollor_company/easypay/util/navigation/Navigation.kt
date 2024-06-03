package com.billion_dollor_company.easypay.util.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.billion_dollor_company.easypay.ui.home.HomeScreen
import com.billion_dollor_company.easypay.ui.selfQR.SelfQRScreen
import com.billion_dollor_company.easypay.util.navigation.graphs.checkBalanceNavGraph
import com.billion_dollor_company.easypay.util.navigation.graphs.transactionNavGraph
import com.core.common.models.NpciKeyInfo
import com.core.common.pref.npciKeys.NpciKeysPref

@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun Navigation(
    navigationProvider: NavigationProvider,
    npciKeyInfo: NpciKeyInfo
) {
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
                        Screen.CheckBalanceRequesterScreen
                    )
                },
                onSelfQRClick = {
                    navController.navigate(
                        Screen.SelfQRScreen
                    )
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

        checkBalanceNavGraph(navController, navigationProvider.checkBalanceFeatureApi, npciKeyInfo)

        transactionNavGraph(navController, navigationProvider.transactionFeatureApi, npciKeyInfo)

    }
}






