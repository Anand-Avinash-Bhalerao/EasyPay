package com.billion_dollor_company.easypay.util.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.billion_dollor_company.easypay.ui.checkBalance.checkBalanceComplete.CheckBalanceCompleteScreen
import com.billion_dollor_company.easypay.ui.checkBalance.checkBalanceRequester.CheckBalanceRequesterScreen
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.Helper
import com.billion_dollor_company.easypay.util.navigation.Screen
import com.cl.ui.navigation.CheckBalanceApi

fun NavGraphBuilder.checkBalanceNavGraph(
    navHostController: NavHostController,
    checkBalanceApi: CheckBalanceApi
) {
    navigation(
        route = "check_balance",
        startDestination = "check_balance_screen"
    ) {
        composable<Screen.CheckBalanceRequesterScreen> { it ->
            val encryptedPassword = it.savedStateHandle.get<String>("encryptedPassword")

            CheckBalanceRequesterScreen(
                isPasswordFetched = encryptedPassword != null,
                navigateToCL = {
                    navHostController.navigate(
                        checkBalanceApi.getPath(
                            upiID = Constants.PayerDetails.UPI_ID,
                            accountNo = Constants.PayerDetails.ACCOUNT_NO,
                            bankName = Constants.PayerDetails.BANK_NAME,
                            publicKey = Constants.Keys.NPCI_PUBLIC_KEY
                        )
                    )
                },
                navigateToCompleteScreen = {
                    navHostController.navigate(
                        Screen.CheckBalanceCompleteScreen(
                            upiID = Constants.PayerDetails.UPI_ID,
                            encryptedPassword = encryptedPassword!!
                        )
                    )
                },
                onBackPress = {
                    navHostController.navigateUp()
                }
            )
        }

        checkBalanceApi.registerGraph(
            navController = navHostController,
            this
        )


        composable<Screen.CheckBalanceCompleteScreen> {

            val passedData = it.toRoute<Screen.CheckBalanceCompleteScreen>()

            CheckBalanceCompleteScreen(
                passedData,
                onBackClick = {
                    navHostController.navigateUp()
                    navHostController.navigateUp()
                }
            )
        }
    }
}