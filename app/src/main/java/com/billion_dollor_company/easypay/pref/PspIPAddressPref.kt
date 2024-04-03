package com.billion_dollor_company.easypay.pref

import kotlinx.coroutines.flow.Flow

interface PspIPAddressPref {
    fun getIP() : Flow<String>
    suspend fun saveIP(ipAddress : String)
}