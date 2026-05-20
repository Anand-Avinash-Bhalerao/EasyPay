package com.billion_dollor_company.easypay.di

import com.billion_dollor_company.easypay.util.navigation.NavigationProvider
import com.npciCore.featureApi.CheckBalanceFeatureApi
import com.npciCore.featureApi.TransactionFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideNavigationProvider(
        checkBalanceFeatureApi: CheckBalanceFeatureApi,
        transactionFeatureApi: TransactionFeatureApi
    ): NavigationProvider {
        return NavigationProvider(checkBalanceFeatureApi, transactionFeatureApi)
    }


}