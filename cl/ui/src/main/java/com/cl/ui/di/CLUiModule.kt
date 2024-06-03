package com.cl.ui.di

import com.cl.ui.navigation.CheckBalanceApiImpl
import com.cl.ui.navigation.TransactionApiImpl
import com.npciCore.featureApi.CheckBalanceFeatureApi
import com.npciCore.featureApi.TransactionFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CLUiModule {

//    @Provides
//    fun provideCheckBalanceApi(): CheckBalanceFeatureApi {
//        return CheckBalanceApiImpl()
//    }
//
//    @Provides
//    fun provideTransactionApi(): TransactionFeatureApi {
//        return TransactionApiImpl()
//    }

}