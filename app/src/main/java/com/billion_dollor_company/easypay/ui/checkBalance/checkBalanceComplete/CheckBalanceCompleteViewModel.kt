package com.billion_dollor_company.easypay.ui.checkBalance.checkBalanceComplete

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.api.CheckBalanceApi
import com.billion_dollor_company.easypay.util.Constants
import com.billion_dollor_company.easypay.util.Helper
import com.billion_dollor_company.easypay.util.navigation.Screen
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.npciCore.common.models.checkBalance.CheckBalanceReqInfo
import com.npciCore.common.models.checkBalance.CheckBalanceResInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CheckBalanceCompleteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    @Inject
    lateinit var checkBalanceApi: CheckBalanceApi

    private val upiID = Constants.PayerDetails.UPI_ID
    private var encryptedPassword = ""

    private var fetchStatus = Constants.Values.LOADING
    private var fetchMessage = ""

    private fun getAccountBalanceInfo() = CheckBalanceReqInfo(
        upiID = upiID,
        encryptedPassword = encryptedPassword
    )

    suspend fun getAccountBalance() {
        val checkBalanceReqInfo = getAccountBalanceInfo()
        withContext(Dispatchers.IO) {
            try {
                // Make your API call here
                val response = checkBalanceApi.getAccountBalance(checkBalanceReqInfo)

                // Check the response and return true or false accordingly
                var statusInfo: CheckBalanceResInfo = if (response.isSuccessful) {
                    response.body()!!
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<CheckBalanceResInfo>() {}.type
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                }

                fetchStatus = statusInfo.status
                fetchMessage = statusInfo.message

                Log.d(Constants.TAG, "The Response is: $response")

            } catch (e: Exception) {
            }
        }
    }



    fun getStatus() = fetchStatus

    fun getMessage() = fetchMessage
    fun init(passedData: Screen.CheckBalanceCompleteScreen) {
        encryptedPassword = Helper.decodeSpecialCharString(
            passedData.encryptedPassword
        )
    }

}