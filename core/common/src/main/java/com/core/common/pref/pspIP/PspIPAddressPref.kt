package com.core.common.pref.pspIP

import kotlinx.coroutines.flow.Flow

interface PspIPAddressPref {
    fun getIP() : Flow<String>
    suspend fun saveIP(ipAddress : String)
}