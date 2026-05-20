package com.core.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.core.common.Constants
import com.core.common.models.NpciKeyInfo
import com.core.common.models.PSPServerInfo
import com.core.common.pref.npciKeys.NpciKeyDatastore
import com.core.common.pref.npciKeys.NpciKeysPref
import com.core.common.pref.npciKeys.NpciKeysPrefImpl
import com.core.common.pref.pspIP.PspIPAddressImpl
import com.core.common.pref.pspIP.PspIPAddressPref
import com.core.common.pref.pspIP.PspIPDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Singleton
    @Provides
    fun providePspDataStore(@ApplicationContext context: Context): PspIPDatastore {
        return PspIPDatastore(
            PreferenceDataStoreFactory.create(
                corruptionHandler = ReplaceFileCorruptionHandler(
                    produceNewData = { emptyPreferences() }
                ),
                produceFile = {
                    context.preferencesDataStoreFile(Constants.Preferences.IP_FILE_NAME)
                }
            )
        )
    }


    @Provides
    fun providePspIPAddressPref(pspIPDatastore: PspIPDatastore): PspIPAddressPref {
        return PspIPAddressImpl(pspIPDatastore.datastore)
    }

    @Provides
    fun providesPSPServerInfo(pspIPDatastore: PspIPDatastore): PSPServerInfo {
        val dataStore = pspIPDatastore.datastore
        var ipAddress: String
        runBlocking {
            ipAddress = dataStore.data.first()[Constants.Preferences.IP_KEY] ?: ""
        }
        return PSPServerInfo(ipAddress)
    }

    @Singleton
    @Provides
    fun provideNpciKeyDataStore(@ApplicationContext context: Context): NpciKeyDatastore {
        return NpciKeyDatastore(
            PreferenceDataStoreFactory.create(
                corruptionHandler = ReplaceFileCorruptionHandler(
                    produceNewData = { emptyPreferences() }
                ),
                produceFile = {
                    context.preferencesDataStoreFile(Constants.Preferences.NPCI_KEY_FILE_NAME)
                }
            )
        )
    }

    @Provides
    fun providesNpciKeyPref(npciKeyDatastore: NpciKeyDatastore): NpciKeysPref {
        return NpciKeysPrefImpl(npciKeyDatastore.datastore)
    }

    @Provides
    fun providesNpciKeyInfo(npciKeyDatastore: NpciKeyDatastore): NpciKeyInfo {
        val dataStore = npciKeyDatastore.datastore
        var publicKey: String
        runBlocking {
            publicKey = dataStore.data.first()[Constants.Preferences.NPCI_KEY] ?: ""
        }
        return NpciKeyInfo(publicKey)
    }

}