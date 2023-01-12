package com.example.cheerboost.application.di

import android.app.Application
import androidx.room.Room
import com.example.cheerboost.feature_setting.data.data_source.SettingDatabase
import com.example.cheerboost.feature_setting.data.repository.SettingRepositoryImpl
import com.example.cheerboost.feature_setting.domain.repository.SettingRepository
import com.example.cheerboost.feature_setting.domain.use_case.GetSetting
import com.example.cheerboost.feature_setting.domain.use_case.InsertSetting
import com.example.cheerboost.feature_setting.domain.use_case.SettingUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingModule {
    @Provides
    @Singleton
    fun providesSettingDatabase(app: Application): SettingDatabase {
        return Room.databaseBuilder(
            app,
            SettingDatabase::class.java,
            SettingDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesSettingRepository(db: SettingDatabase): SettingRepository {
        return SettingRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun providesSettingUseCases(repository: SettingRepository): SettingUseCases {
        return SettingUseCases(
            getSetting = GetSetting(repository),
            insertSetting = InsertSetting(repository),
        )
    }
}
