package com.cl.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cl.ui.passedData.TransactionPassedData
import com.cl.ui.screens.transaction.TransactionPinScreen
import com.npciCore.featureApi.FeatureApi


interface TransactionApi : FeatureApi {
    override val startDestination: String get() = "transactionPinEnterScreen"
    override val route: String get() = "transactionRoute"

    fun getPath(
        payeeFullName: String,
        payeeUpiID: String,
        payerUpiID: String,
        payerBankName: String,
        payerAccountNumber: String,
        amountToPay: String
    ): String =
        "$startDestination/$payeeFullName/$payeeUpiID/$payerUpiID/$payerBankName/$payerAccountNumber/$amountToPay"
}

class TransactionApiImpl : TransactionApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalTransactionApi.registerGraph(
            navController,
            navGraphBuilder
        )
    }

}

internal object InternalTransactionApi : TransactionApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {

        val startDestination = this.startDestination
        val route = this.route

        navGraphBuilder.navigation(
            startDestination = startDestination,
            route = route
        ) {
            composable(
                route = "${startDestination}/{payeeFullName}/{payeeUpiID}/{payerUpiID}/{payerBankName}/{payerAccountNumber}/{amountToPay}",

                arguments = listOf(
                    navArgument("payeeFullName") {
                        type = NavType.StringType
                    },
                    navArgument("payeeUpiID") {
                        type = NavType.StringType
                    },
                    navArgument("payerUpiID") {
                        type = NavType.StringType
                    },
                    navArgument("payerBankName") {
                        type = NavType.StringType
                    },
                    navArgument("payerAccountNumber") {
                        type = NavType.StringType
                    },
                    navArgument("amountToPay") {
                        type = NavType.StringType
                    }
                )
            ) {
                val payeeFullName = it.arguments?.getString("payeeFullName")!!
                val payeeUpiID = it.arguments?.getString("payeeUpiID")!!
                val payerUpiID = it.arguments?.getString("payerUpiID")!!
                val payerBankName = it.arguments?.getString("payerBankName")!!
                val payerAccountNumber = it.arguments?.getString("payerAccountNumber")!!
                val amountToPay = it.arguments?.getString("amountToPay")!!

                val passedData = TransactionPassedData(
                    payeeFullName = payeeFullName,
                    payeeUpiID = payeeUpiID,
                    payerUpiID = payerUpiID,
                    payerBankName = payerBankName,
                    payerAccountNumber = payerAccountNumber,
                    amountToPay = amountToPay
                )
                TransactionPinScreen(passedData = passedData) { info ->
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "encryptedPassword",
                        info.encryptedPassword
                    )
                    navController.navigateUp()
                }
            }
        }
    }
}
