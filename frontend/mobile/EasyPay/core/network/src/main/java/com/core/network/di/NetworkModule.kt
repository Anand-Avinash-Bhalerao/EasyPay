package com.core.network.di

import com.billion_dollor_company.easypay.api.CheckBalanceApi
import com.billion_dollor_company.easypay.api.StartTransactionApi
import com.core.common.models.PSPServerInfo
import com.core.network.NetworkConstants
import com.core.network.api.FetchKeysApi
import com.core.network.api.RegistrationClAPI
import com.core.network.api.UserInfoApi
import com.core.network.models.fetchKeys.FetchKeysResInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(
        pspServerInfo: PSPServerInfo
    ): Retrofit {

        val ipAddress = pspServerInfo.ipAddress
        val url = NetworkConstants.PspServer.getBaseURL(ipAddress)
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

    @Singleton
    @Provides
    fun providesRegistrationAPI(retrofit: Retrofit): RegistrationClAPI {
        return retrofit.create(RegistrationClAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesFetchKeysAPI(retrofit: Retrofit): FetchKeysApi {
        return retrofit.create(FetchKeysApi::class.java)
    }

}