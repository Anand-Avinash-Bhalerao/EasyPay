package com.cl.ui.di

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
class CLUiModule {

    @Provides
    fun provideCheckBalanceApi() : CheckBalanceApi {
        return CheckBalanceApiImpl()
    }

    @Provides
    fun provideTransactionApi() : TransactionApi {
        return TransactionApiImpl()
    }

}