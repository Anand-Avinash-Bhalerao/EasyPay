package com.billion_dollor_company.easypay.util

sealed class Screen(val route :String){
    object HomeScreen : Screen("home")
    object ScanScreen : Screen("scan")
    object AmountEnterScreen : Screen("amountEnter")
}

