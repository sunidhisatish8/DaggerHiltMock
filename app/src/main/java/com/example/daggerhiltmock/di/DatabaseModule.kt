package com.example.daggerhiltmock.di

import android.content.Context
import androidx.room.Room
import com.example.daggerhiltmock.model.localRepository.AppDatabase
import com.example.daggerhiltmock.model.localRepository.PhoneDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        val room = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "phone_details"
        ).fallbackToDestructiveMigration()
            .build()
        return room
    }

    @Provides
    fun providePhoneDao(appDatabase: AppDatabase): PhoneDao {
        return appDatabase.getPhoneDao()
    }
}