package com.cl.ui.util

import android.text.TextUtils.replace
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


/*
we need this functions because when we pass the public key from app to cl, it is sent using compose navigation.
in navigation we separate the parameters using / character.
but the public key could contain / + = characters.
/ causes problems in navigation because compose thinks it is the end of current parameter and start of another parameter.
+ gets replaced with a blank character in URL encoder.

we need to somehow change them to something safe. so the public key does not corrupt.
 */
object Helper {

    fun encodeForSpecialCharacter(str: String): String {
        return str.replace("/", "!!!!!!!")
    }

    fun decodeSpecialCharString(str: String): String {
        return str.replace("!!!!!!!", "/")
    }
}