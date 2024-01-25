package com.billion_dollor_company.easypay.di

import com.billion_dollor_company.easypay.api.UserInfoApi
import com.billion_dollor_company.easypay.util.Constants
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
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Constants.Server.PspServer.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun userInfoAPI(retrofit: Retrofit) : UserInfoApi{
        return retrofit.create(UserInfoApi::class.java)
    }
}