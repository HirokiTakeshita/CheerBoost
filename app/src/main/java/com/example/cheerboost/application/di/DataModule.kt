package com.example.cheerboost.application.di

import android.app.Application
import androidx.room.Room
import com.example.cheerboost.feature_data.data.Converters
import com.example.cheerboost.feature_data.data.data_source.WorkoutSessionDatabase
import com.example.cheerboost.feature_data.data.repository.WorkoutSessionRepositoryImpl
import com.example.cheerboost.feature_data.domain.repository.WorkoutSessionRepository
import com.example.cheerboost.feature_data.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesWorkoutSessionDatabase(app: Application): WorkoutSessionDatabase {
        return Room.databaseBuilder(
            app,
            WorkoutSessionDatabase::class.java,
            WorkoutSessionDatabase.DATABASE_NAME
        ).addTypeConverter(Converters()).build()
    }

    @Provides
    @Singleton
    fun providesWorkoutSessionRepository(db: WorkoutSessionDatabase): WorkoutSessionRepository {
        return WorkoutSessionRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideWorkoutSessionUseCases(
        repository: WorkoutSessionRepository
    ): WorkoutSessionUseCases {
        return WorkoutSessionUseCases(
            getWorkoutSessions = GetWorkoutSessions(repository),
            getWorkoutSession = GetWorkoutSession(repository),
            insertWorkoutSession = InsertWorkoutSession(repository),
            deleteWorkoutSession = DeleteWorkoutSession(repository)
        )
    }
}
