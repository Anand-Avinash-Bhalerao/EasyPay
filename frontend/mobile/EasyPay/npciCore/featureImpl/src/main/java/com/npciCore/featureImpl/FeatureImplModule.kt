package com.npciCore.featureImpl

import com.cl.domain.registration.RegistrationImpl
import com.cl.ui.navigation.CheckBalanceApiImpl
import com.cl.ui.navigation.TransactionApiImpl
import com.npciCore.featureApi.CheckBalanceFeatureApi
import com.npciCore.featureApi.RegistrationCL
import com.npciCore.featureApi.TransactionFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class FeatureImplModule {

    @Provides
    fun providesCheckBalanceApi(): CheckBalanceFeatureApi {
        return CheckBalanceApiImpl()
    }

    @Provides
    fun providesTransactionApi(): TransactionFeatureApi {
        return TransactionApiImpl()
    }

    @Provides
    fun providesRegistrationCL(): RegistrationCL {
        return RegistrationImpl()
    }

}

