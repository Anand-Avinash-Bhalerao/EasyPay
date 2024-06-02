package com.npciCore.common

object Constants{
    // These are hard coded values. Need to replace with DB in future.
    object PayerDetails {
        const val FIRST_NAME = "Anand"
        const val MIDDLE_NAME = "Avinash"
        const val LAST_NAME = "Bhalerao"
        const val UPI_ID = "anandbhalerao@oksbi"
        const val ACCOUNT_NO = "SBI1000000"
        const val BANK_NAME = "State Bank of India"
        const val PHONE_NO = "9175799277"

//        const val FIRST_NAME = "Shruti"
//        const val MIDDLE_NAME = "Sanjay"
//        const val LAST_NAME = "Deokar"
//        const val UPI_ID = "shrutideokar@okicici"
//        const val ACCOUNT_NO = "ICICI1000000"
//        const val BANK_NAME = "ICICI"
//        const val PHONE_NO = "9325591558"

        const val FULL_NAME = "$FIRST_NAME $MIDDLE_NAME $LAST_NAME"
    }
}


// constatns for navigation.
object NpciScreens{
    object CheckBalanceFeature{
        const val nestedRoute = "checkBalanceRoute"
        const val checkBalancePinScreen = "checkBalancePinScreen"
    }

    object TransactionFeature{
        const val nestedRoute = "transactionRoute"
        const val transactionPinScreen = "transactionPinScreen"
    }
}