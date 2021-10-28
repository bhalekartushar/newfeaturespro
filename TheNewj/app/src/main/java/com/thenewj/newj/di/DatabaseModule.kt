package com.thenewj.newj.di

import android.content.Context
import androidx.room.Room
import com.thenewj.newj.data.local.AppDatabase
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
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "newj_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAuthMobileRequestDao(db: AppDatabase) = db.authDao()
}