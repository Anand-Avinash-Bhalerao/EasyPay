package com.billion_dollor_company.easypay.util

sealed class Screen(val route :String){
    object Home : Screen("home")
}
