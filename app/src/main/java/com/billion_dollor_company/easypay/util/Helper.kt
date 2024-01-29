package com.billion_dollor_company.easypay.util

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
}