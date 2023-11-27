package com.billion_dollor_company.easypay.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.models.RecentInteractionInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel @Inject constructor() : ViewModel() {
    val dummyRecentList = listOf(
        RecentInteractionInfo(
            firstName = "Baburao",
            userImage = R.drawable.male1
        ),
        RecentInteractionInfo(
            firstName = "Shruti",
            userImage = R.drawable.female1
        ),
        RecentInteractionInfo(
            firstName = "Raju",
            userImage = R.drawable.male2
        ),
        RecentInteractionInfo(
            firstName = "Sanya",
            userImage = R.drawable.female2
        ),
        RecentInteractionInfo(
            firstName = "Rucha",
            userImage = R.drawable.female4
        ),
        RecentInteractionInfo(
            firstName = "Abhishek",
            userImage = R.drawable.male3
        ),
        RecentInteractionInfo(
            firstName = "Aisha",
            userImage = R.drawable.female3
        ),
        RecentInteractionInfo(
            firstName = "Shyam",
            userImage = R.drawable.male4
        ),
    )
}