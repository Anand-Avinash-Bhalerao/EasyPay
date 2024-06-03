package com.billion_dollor_company.easypay.util.navigation

import com.npciCore.featureApi.CheckBalanceFeatureApi
import com.npciCore.featureApi.TransactionFeatureApi

data class NavigationProvider(
    val checkBalanceFeatureApi: CheckBalanceFeatureApi,
    val transactionFeatureApi: TransactionFeatureApi
)
