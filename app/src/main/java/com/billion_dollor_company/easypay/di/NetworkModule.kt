package com.billion_dollor_company.easypay.di

import android.util.Log
import com.billion_dollor_company.easypay.api.CheckBalanceApi
import com.billion_dollor_company.easypay.api.StartTransactionApi
import com.billion_dollor_company.easypay.api.UserInfoApi
import com.billion_dollor_company.easypay.models.PSPServerInfo
import com.billion_dollor_company.easypay.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @OptIn(DelicateCoroutinesApi::class)
    @Singleton
    @Provides
    fun providesRetrofit(serverInfo : PSPServerInfo): Retrofit {
        Log.d(Constants.TAG, "The server info is: "+serverInfo.ipAddress)
        return Retrofit
            .Builder()
            .baseUrl(Constants.Server.PspServer.BASE_URL)
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