package com.billion_dollor_company.easypay.util

import android.net.InetAddresses
import android.os.Build
import android.util.Patterns
import com.billion_dollor_company.easypay.R
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.DecimalFormat

object Helper {

    fun encodeForSpecialCharacter(str: String): String {
        val encoded = URLEncoder.encode(str, StandardCharsets.UTF_8.toString())
        return encoded
    }

    fun decodeSpecialCharString(str: String): String {
        val replace = "!!!!!!"
        val replaceWith = "/"
        return str.replace(replace, replaceWith)
    }

    fun isIpValid(ip: String): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            InetAddresses.isNumericAddress(ip)
        } else {
            Patterns.IP_ADDRESS.matcher(ip).matches()
        }
    }

    fun getFormattedAmount(amountString : String) : String {
        val formatter = DecimalFormat("##,##,##,##,###.##")
        val number = amountString.toDouble()
        return formatter.format(number)
    }

    fun getBankIcon(): Int {
        return when (Constants.PayerDetails.BANK_NAME.lowercase()) {
            "state bank of india" -> R.drawable.sbi
            "icici" -> R.drawable.icici
            "idbi" -> R.drawable.idbi
            "axis" -> R.drawable.axis
            else -> R.drawable.sbi
        }
    }

}