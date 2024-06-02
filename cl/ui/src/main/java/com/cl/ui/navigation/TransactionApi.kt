package com.cl.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cl.ui.passedData.TransactionPassedData
import com.cl.ui.screens.transaction.TransactionPinScreen
import com.cl.ui.util.Helper
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
        amountToPay: String,
        publicKey: String
    ): String =
        "$startDestination/$payeeFullName/$payeeUpiID/$payerUpiID/$payerBankName/$payerAccountNumber/$amountToPay/${Helper.encodeForSpecialCharacter(publicKey)}"
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
        navGraphBuilder.navigation(
            startDestination = startDestination,
            route = route
        ) {
            composable(
                route = "${startDestination}/{payeeFullName}/{payeeUpiID}/{payerUpiID}/{payerBankName}/{payerAccountNumber}/{amountToPay}/{publicKey}",

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
                    },
                    navArgument("publicKey") {
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
                val publicKey = it.arguments?.getString("publicKey")!!

                val passedData = TransactionPassedData(
                    payeeFullName = payeeFullName,
                    payeeUpiID = payeeUpiID,
                    payerUpiID = payerUpiID,
                    payerBankName = payerBankName,
                    payerAccountNumber = payerAccountNumber,
                    amountToPay = amountToPay,
                    publicKey = Helper.decodeSpecialCharString(publicKey)
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
