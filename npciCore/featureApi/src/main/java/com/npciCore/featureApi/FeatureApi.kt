package com.npciCore.featureApi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {

    val startDestination: String
    val route: String

    fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    )

}

interface CheckBalanceFeatureApi : FeatureApi {
    fun getPath(
        accountNo: String,
        bankName: String,
        upiID: String,
        publicKey: String,
    ): String
}

interface TransactionFeatureApi : FeatureApi {
    fun getPath(
        payeeFullName: String,
        payeeUpiID: String,
        payerUpiID: String,
        payerBankName: String,
        payerAccountNumber: String,
        amountToPay: String,
        publicKey: String
    ): String
}
