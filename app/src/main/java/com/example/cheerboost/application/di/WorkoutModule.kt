package com.example.cheerboost.application.di

import android.app.Application
import com.example.cheerboost.feature_count.domain.sensor.GravitySensor
import com.example.cheerboost.feature_count.domain.sensor.LinearAccelerationSensor
import com.example.cheerboost.feature_count.domain.sensor.MeasurableSensor
import com.example.cheerboost.feature_count.domain.voice.Avatar
import com.example.cheerboost.feature_count.domain.voice.VoiceAvatar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkoutModule {
    @Provides
    @Singleton
    @Named("Gravity")
    fun provideGravitySensor(app: Application): MeasurableSensor {
        return GravitySensor(app)
    }

    @Provides
    @Singleton
    @Named("LinearAcceleration")
    fun provideLinearAccelerationSensor(app: Application): MeasurableSensor {
        return LinearAccelerationSensor(app)
    }

    @Provides
    @Singleton
    fun provideVoiceAvatar(app: Application): Avatar {
        return VoiceAvatar(app)
    }
}
