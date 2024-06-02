package com.cl.domain.di

import com.cl.domain.useCases.EncryptPasswordUseCase
import com.cl.domain.util.DomainConstants
import com.cl.domain.util.cryptography.EncryptionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainLayerModule {

    @Provides
    fun provideEncryptPasswordUseCase(): EncryptPasswordUseCase {
        return EncryptPasswordUseCase()
    }

}