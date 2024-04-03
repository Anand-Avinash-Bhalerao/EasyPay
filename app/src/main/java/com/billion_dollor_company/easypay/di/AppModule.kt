package com.billion_dollor_company.easypay.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.lifecycle.viewModelScope
import com.billion_dollor_company.easypay.models.PSPServerInfo
import com.billion_dollor_company.easypay.pref.PspIPAddressImpl
import com.billion_dollor_company.easypay.pref.PspIPAddressPref
import com.billion_dollor_company.easypay.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = {
                context.preferencesDataStoreFile(Constants.Preferences.IP_FILE_NAME)
            }
        )
    }

    @Provides
    fun providePspIPAddressPref(dataStore: DataStore<Preferences>): PspIPAddressPref =
        PspIPAddressImpl(dataStore)

    @OptIn(DelicateCoroutinesApi::class)
    @Provides
    fun providesPSPServerInfo(pspIPAddressPref: PspIPAddressPref): PSPServerInfo {
        return PSPServerInfo(
            pspIPAddressPref.getIP().stateIn(
                scope = GlobalScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = ""
            ).value
        )
    }
}