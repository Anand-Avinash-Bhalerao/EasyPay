package com.billion_dollor_company.easypay.util.navigation

import com.cl.ui.navigation.CheckBalanceApi
import com.cl.ui.navigation.TransactionApi

data class NavigationProvider(
    val checkBalanceApi: CheckBalanceApi,
    val transactionApi: TransactionApi
)
