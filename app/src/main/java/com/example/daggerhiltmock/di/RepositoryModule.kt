package com.example.daggerhiltmock.di

import com.example.daggerhiltmock.model.localRepository.PhoneDao
import com.example.daggerhiltmock.model.remoteRepository.ApiService
import com.example.daggerhiltmock.repository.IRepository
import com.example.daggerhiltmock.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(
        repository: Repository
    ): IRepository
}