package com.billion_dollor_company.easypay.ui.scan.components

import android.content.Context
import android.util.Log
import com.billion_dollor_company.easypay.util.Constants
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.flow.MutableStateFlow

class BarCodeScanner(
    context: Context,
) {
    private val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE
        )
        .enableAutoZoom()
        .build()

    private val scanner = GmsBarcodeScanning.getClient(context, options)
    val barCodeResults = MutableStateFlow<String?>(null)
    suspend fun startScan(
        onCancelled: () -> Unit,
        onSuccess: (String) -> Unit,
    ) {
        try {
            scanner.startScan()
                .addOnSuccessListener { barcode ->
                    Log.d(Constants.TAG, "ScanScreen: The value is ${barcode.displayValue}")
                    barCodeResults.value = barcode.displayValue
                    onSuccess(barCodeResults.value!!)
                }
                .addOnCanceledListener {
                    onCancelled()
                }
                .addOnFailureListener {
                    barCodeResults.value = "failed"
                }
        } catch (e: Exception) {

        }
    }
}