package com.core.common.pref.npciKeys

import kotlinx.coroutines.flow.Flow

interface NpciKeysPref {
    fun getKey() : Flow<String>
    suspend fun saveKey(key : String)
}