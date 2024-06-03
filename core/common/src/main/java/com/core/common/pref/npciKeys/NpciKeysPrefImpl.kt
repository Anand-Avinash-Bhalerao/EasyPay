package com.core.common.pref.npciKeys

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.core.common.Constants
import com.core.common.pref.pspIP.PspIPAddressPref
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


class NpciKeysPrefImpl(private val datastore: DataStore<Preferences>) : NpciKeysPref {
    override fun getKey(): Flow<String> {
        return datastore.data.catch { emit(emptyPreferences()) }.map {
            it[Constants.Preferences.NPCI_KEY] ?: ""
        }
    }

    override suspend fun saveKey(key: String) {
        datastore.edit {
            it[Constants.Preferences.NPCI_KEY] = key
        }
    }
}