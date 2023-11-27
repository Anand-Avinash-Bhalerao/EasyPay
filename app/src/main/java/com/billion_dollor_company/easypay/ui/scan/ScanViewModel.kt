package com.billion_dollor_company.easypay.ui.scan

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.billion_dollor_company.easypay.ui.scan.components.BarCodeScanner
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class ScanViewModel @Inject constructor() : ViewModel() {
    var barCodeResult = mutableStateOf("")
}