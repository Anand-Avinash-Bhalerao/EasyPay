package com.core.common.pref.pspIP

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.core.common.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


class PspIPAddressImpl(private val datastore: DataStore<Preferences>) : PspIPAddressPref {
    override fun getIP(): Flow<String> {
        return datastore.data.catch { emit(emptyPreferences()) }.map {
            it[Constants.Preferences.IP_KEY] ?: ""
        }
    }

    override suspend fun saveIP(ipAddress: String) {
        datastore.edit {
            it[Constants.Preferences.IP_KEY] = ipAddress
        }
    }
}