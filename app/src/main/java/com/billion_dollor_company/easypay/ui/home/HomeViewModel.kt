package com.billion_dollor_company.easypay.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.models.RecentInteractionInfo
import com.billion_dollor_company.easypay.pref.PspIPAddressPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel @Inject constructor(private val pspIPAddressPref: PspIPAddressPref) : ViewModel() {
    val dummyRecentList = listOf(
        RecentInteractionInfo(
            firstName = "Akshay",
            middleName = "Avinash",
            lastName = "Bhalerao",
            mobileNo = "7558261369",
            upiID  = "akshaybhalerao@oksbi",
            userImage = R.drawable.male1
        ),
        RecentInteractionInfo(
            firstName = "Shruti",
            middleName = "Sanjay",
            lastName = "Deokar",
            mobileNo = "932591558",
            upiID  = "shrutideokar@okicici",
            userImage = R.drawable.female1
        ),
        RecentInteractionInfo(
            firstName = "Rucha",
            middleName = "Ramesh",
            lastName = "Jadhav",
            mobileNo = "8805343429",
            upiID  = "ruchajadhav@okaxis",
            userImage = R.drawable.female4
        ),
        RecentInteractionInfo(
            firstName = "Abhishek",
            middleName = "",
            lastName = "Soundalgekar",
            mobileNo = "7020458963",
            upiID  = "abhisheksoundalgekar@okidbi",
            userImage = R.drawable.male3
        )
    )

    fun saveIPAddress(ipAddress : String){
        viewModelScope.launch {
            pspIPAddressPref.saveIP(ipAddress)
        }
    }

    fun getIPAddress() : StateFlow<String> {
        return pspIPAddressPref.getIP().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ""
        )
    }
}