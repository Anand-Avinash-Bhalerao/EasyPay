package com.cl.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cl.ui.passedData.CheckBalancePassedData
import com.cl.ui.screens.checkBalance.CheckBalancePinScreen
import com.npciCore.featureApi.FeatureApi

interface CheckBalanceApi : FeatureApi {
    override val startDestination: String get() = "checkBalancePinEnterScreen"
    override val route: String get() = "checkBalanceRoute"

    fun getPath(
        accountNo: String,
        bankName: String,
        upiID: String,
    ): String = "$startDestination/$upiID/$bankName/$accountNo"

}

class CheckBalanceApiImpl : CheckBalanceApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalCheckBalanceApi.registerGraph(
            navController,
            navGraphBuilder
        )
    }
}

internal object InternalCheckBalanceApi : CheckBalanceApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        val startDestination = this.startDestination
        val route = this.route
        navGraphBuilder.navigation(
            startDestination = "$startDestination/{upiID}/{bankName}/{accountNo}",
            route = route,

            ) {
            composable(
                route = "$startDestination/{upiID}/{bankName}/{accountNo}",
                arguments = listOf(
                    navArgument("upiID") {
                        type = NavType.StringType
                    },
                    navArgument("bankName") {
                        type = NavType.StringType
                    },
                    navArgument("accountNo") {
                        type = NavType.StringType
                    },
                )
            ) {

                val upiID = it.arguments?.getString("upiID")!!
                val bankName = it.arguments?.getString("bankName")!!
                val accountNo = it.arguments?.getString("accountNo")!!

                val passedData = CheckBalancePassedData(
                    upiID = upiID,
                    bankName = bankName,
                    accountNo = accountNo
                )

                CheckBalancePinScreen(
                    passedData,
                    onSubmitClick = { checkBalanceInfo ->
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "encryptedPassword",
                            checkBalanceInfo.encryptedPassword
                        )
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
