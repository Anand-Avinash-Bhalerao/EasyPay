package com.billion_dollor_company.easypay

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.billion_dollor_company.easypay.ui.theme.AppTheme
import com.billion_dollor_company.easypay.util.navigation.Navigation
import com.billion_dollor_company.easypay.util.navigation.NavigationProvider
import com.core.common.models.NpciKeyInfo
import com.core.common.pref.npciKeys.NpciKeysPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    @Inject
    lateinit var npciKeyInfo: NpciKeyInfo

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Navigation(
                    navigationProvider,
                    npciKeyInfo
                )
            }
        }
    }
}
