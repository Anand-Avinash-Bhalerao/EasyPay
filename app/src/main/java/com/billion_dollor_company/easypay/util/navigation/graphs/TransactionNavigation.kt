package com.billion_dollor_company.easypay.util.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.billion_dollor_company.easypay.models.PayeeInfo
import com.billion_dollor_company.easypay.ui.transaction.amount.AmountEnterScreen
import com.billion_dollor_company.easypay.ui.transaction.scan.ScanScreen
import com.billion_dollor_company.easypay.ui.transaction.transactionComplete.TransactionCompleteScreen
import com.billion_dollor_company.easypay.ui.transaction.transactionRequestor.TransactionCLReqScreen
import com.billion_dollor_company.easypay.util.navigation.Screen
import com.cl.ui.navigation.TransactionApi


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.transactionNavGraph(
    navHostController: NavHostController,
    transactionApi: TransactionApi
) {
    navigation<Screen.TransactionRoute>(
        startDestination = Screen.AmountEnterScreen(
            fullName = "",
            phoneNo = "",
            upiID = "",
            imageID = 0
        )
    ) {


        composable<Screen.ScanScreen> {
            ScanScreen(
                onScanned = { payeeInfo: PayeeInfo ->
                    navHostController.navigate(
                        Screen.AmountEnterScreen(
                            fullName = payeeInfo.name,
                            phoneNo = payeeInfo.phoneNo,
                            upiID = payeeInfo.upiID,
                            imageID = payeeInfo.imageID
                        )
                    )
                },
                onCanceled = {
                    navHostController.navigateUp()
                }
            )
        }


        composable<Screen.AmountEnterScreen> {
            val passedData = it.toRoute<Screen.AmountEnterScreen>()
            AmountEnterScreen(
                passedData,
                onBackClick = {
                    navHostController.navigate(route = Screen.HomeScreen)
                },
                onPayClick = { info ->
                    navHostController.navigate(
                        Screen.TransactionPinReqScreen(
                            payeeFullName = info.payeeFullName,
                            payeeUpiID = info.payeeUpiID,
                            payerUpiID = info.payerUpiID,
                            payerBankName = info.bankName,
                            payerAccountNumber = info.accountNo,
                            amountToPay = info.amountToTransfer
                        )
                    )
                }
            )
        }

        composable<Screen.TransactionPinReqScreen> {
            val passedData = it.toRoute<Screen.TransactionPinReqScreen>()
            val encryptedPassword = it.savedStateHandle.get<String>("encryptedPassword")
            TransactionCLReqScreen(
                isPasswordFetched = encryptedPassword != null,
                navigateToCL = {
                    navHostController.navigate(
                        transactionApi.getPath(
                            payeeFullName = passedData.payeeFullName,
                            payeeUpiID = passedData.payeeUpiID,
                            payerUpiID = passedData.payerUpiID,
                            payerBankName = passedData.payerBankName,
                            payerAccountNumber = passedData.payerAccountNumber,
                            amountToPay = passedData.amountToPay
                        )
                    )
                },
                navigateToCompleteScreen = {
                    navHostController.navigate(
                        Screen.TransactionCompleteScreen(
                            payeeFullName = passedData.payeeFullName,
                            payeeUpiID = passedData.payeeUpiID,
                            payerUpiID = passedData.payerUpiID,
                            payerBankName = passedData.payerBankName,
                            payerAccountNumber = passedData.payerAccountNumber,
                            amount = passedData.amountToPay,
                            encryptedPassword = encryptedPassword!!
                        )
                    )
                }
            )
        }

        transactionApi.registerGraph(
            navController = navHostController,
            this
        )

        composable<Screen.TransactionCompleteScreen> {

            val passedData = it.toRoute<Screen.TransactionCompleteScreen>()
            TransactionCompleteScreen(
                passedData,
                onBackPress = {
                    navHostController.navigateUp()
                    navHostController.navigateUp()
                    navHostController.navigateUp()
                }
            )
        }

    }
}