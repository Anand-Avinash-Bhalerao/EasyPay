package com.billion_dollor_company.easypay.di

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.billion_dollor_company.easypay.api.CheckBalanceApi
import com.billion_dollor_company.easypay.api.StartTransactionApi
import com.billion_dollor_company.easypay.api.UserInfoApi
import com.billion_dollor_company.easypay.models.PSPServerInfo
import com.billion_dollor_company.easypay.pref.PspIPAddressPref
import com.billion_dollor_company.easypay.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @OptIn(DelicateCoroutinesApi::class)
    @Singleton
    @Provides
    fun providesRetrofit(
        datastore: DataStore<Preferences>
    ): Retrofit {

        var ipAddress = ""
        runBlocking {
            ipAddress = datastore.data.first()[Constants.Preferences.IP_KEY] ?: ""
        }
        val url = Constants.Server.PspServer.getBaseURL(ipAddress)
        Log.d(Constants.TAG, "the ip address is $ipAddress and the url is $url")
        return Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun providesUserInfoAPI(retrofit: Retrofit): UserInfoApi {
        return retrofit.create(UserInfoApi::class.java)
    }

    @Singleton
    @Provides
    fun providesStartTransactionAPI(retrofit: Retrofit): StartTransactionApi {
        return retrofit.create(StartTransactionApi::class.java)
    }

    @Singleton
    @Provides
    fun providesCheckBalanceAPI(retrofit: Retrofit): CheckBalanceApi {
        return retrofit.create(CheckBalanceApi::class.java)
    }
}