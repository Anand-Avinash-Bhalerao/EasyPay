package com.billion_dollor_company.easypay.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.models.RecentInteractionInfo
import com.billion_dollor_company.easypay.util.Constants
import com.core.common.models.NpciKeyInfo
import com.core.common.models.PSPServerInfo
import com.core.common.pref.npciKeys.NpciKeysPref
import com.core.common.pref.pspIP.PspIPAddressPref
import com.core.network.api.FetchKeysApi
import com.core.network.api.RegistrationClAPI
import com.core.network.models.registration.RegistrationCLReqInfo
import com.npciCore.featureApi.RegistrationCL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel @Inject constructor(
    private val pspIPAddressPref: PspIPAddressPref,
    private val npciKeysPref: NpciKeysPref,
    private val pspServerInfo: PSPServerInfo,
    private val registrationCL: RegistrationCL,
    private val registrationClAPI: RegistrationClAPI,
    private val fetchKeysApi: FetchKeysApi,
    private val npciKeyInfo: NpciKeyInfo
) : ViewModel() {

    private var wasRegistrationSuccessful = false
    private var wasKeyFetchSuccessful = false

    val dummyRecentList = listOf(
        RecentInteractionInfo(
            firstName = "Akshay",
            middleName = "Avinash",
            lastName = "Bhalerao",
            mobileNo = "7558261369",
            upiID = "akshaybhalerao@oksbi",
            userImage = R.drawable.male1
        ),
        RecentInteractionInfo(
            firstName = "Shruti",
            middleName = "Sanjay",
            lastName = "Deokar",
            mobileNo = "932591558",
            upiID = "shrutideokar@okicici",
            userImage = R.drawable.female1
        ),
        RecentInteractionInfo(
            firstName = "Rucha",
            middleName = "Ramesh",
            lastName = "Jadhav",
            mobileNo = "8805343429",
            upiID = "ruchajadhav@okaxis",
            userImage = R.drawable.female4
        ),
        RecentInteractionInfo(
            firstName = "Abhishek",
            middleName = "",
            lastName = "Soundalgekar",
            mobileNo = "7020458963",
            upiID = "abhisheksoundalgekar@okidbi",
            userImage = R.drawable.male3
        )
    )

    fun saveIPAddress(ipAddress: String) {
        viewModelScope.launch {
            pspIPAddressPref.saveIP(ipAddress)
        }
    }

    fun getIPAddress(): StateFlow<String> {

        return pspIPAddressPref.getIP().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ""
        )
    }

    fun getIp(): String = pspServerInfo.ipAddress


    suspend fun register(): Boolean {
        try {
            val registerCl = registrationCL
            val challenge = registerCl.getChallenge()
            val reqInfo = RegistrationCLReqInfo(challenge)
            Log.d("TAGGGG", "The request for the server is ($reqInfo)")
            val response = registrationClAPI.register(reqInfo).body()!!
            Log.d("TAGGGG", "The response from the server is $response")
            wasRegistrationSuccessful = if (response.status == Constants.Values.SUCCESS) {
                registerCl.verifyKey(response.message)
            } else {
                false
            }
            Log.d("TAGGGG", "is it same? $wasRegistrationSuccessful")
        } catch (e: Exception) {
            Log.d("TAGGGG", "caught exception")
        }
        return wasRegistrationSuccessful
    }

    suspend fun getKeys(): Boolean {

        try {
            val response = fetchKeysApi.getKeys().body()!!
            Log.d("TAGGGG", "The response from the server is $response")
            val key = response.message
            npciKeysPref.saveKey(key)
            wasKeyFetchSuccessful = response.status == Constants.Values.SUCCESS
        } catch (ignored: Exception) { }

        return wasKeyFetchSuccessful
    }

    fun isKeyStored(): Boolean {
        return npciKeyInfo.publicKey != ""
    }


}