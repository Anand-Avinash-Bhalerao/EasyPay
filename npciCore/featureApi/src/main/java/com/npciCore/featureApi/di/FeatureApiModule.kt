package com.npciCore.featureApi.di

import com.cl.ui.navigation.CheckBalanceApi
import com.cl.ui.navigation.CheckBalanceApiImpl
import com.cl.ui.navigation.TransactionApi
import com.cl.ui.navigation.TransactionApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FeatureApiModule {

    @Provides
    fun provideCheckBalanceApi(checkBalanceApi: CheckBalanceApi): CheckBalanceApi {
        return checkBalanceApi
    }

    @Provides
    fun provideTransactionApi(transactionApi: TransactionApi): TransactionApi {
        return transactionApi
    }

}