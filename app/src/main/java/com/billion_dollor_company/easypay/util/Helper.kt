package com.billion_dollor_company.easypay.util

import android.net.InetAddresses
import android.os.Build
import android.util.Patterns
import java.text.DecimalFormat

object Helper {

    fun encodeSpecialCharString(str: String): String {
        val replace = "/"
        val replaceWith = "!!!!!!"
        return str.replace(replace, replaceWith)
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

}