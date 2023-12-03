package com.billion_dollor_company.easypay.ui.scan

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.billion_dollor_company.easypay.R
import com.billion_dollor_company.easypay.models.PayeeInfo
import com.billion_dollor_company.easypay.ui.scan.components.BarCodeScanner
import com.billion_dollor_company.easypay.util.Constants
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScanScreen(
    onScanned: (PayeeInfo) -> Unit,
    onCanceled: () -> Unit
) {
    val viewModel: ScanViewModel = hiltViewModel()
    val context = LocalContext.current
    val barCodeScanner = BarCodeScanner(context)
    val scope = rememberCoroutineScope()


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LaunchedEffect(Unit) {
                    barCodeScanner.startScan(
                        onCancelled = onCanceled,
                        onSuccess = {
                            viewModel.barCodeResult.value = it
                            val uri = Uri.parse(it)
                            val name = uri.getQueryParameter("name")?.replace("_", " ") ?: ""
                            val upiId = uri.getQueryParameter("upiID") ?: ""
                            val phoneNo = uri.getQueryParameter("phoneNo") ?: ""

                            val imagelist = listOf(
                                R.drawable.male1,
                                R.drawable.male2,
                                R.drawable.male3,
                                R.drawable.male4,
                            )
                            val imageID = imagelist.random()

                            val payeeInfo = PayeeInfo(
                                name = name,
                                phoneNo = phoneNo,
                                upiID = upiId,
                                imageID = imageID
                            )
                            onScanned(payeeInfo)
                        }
                    )
                }
            }
        }
    }
}
