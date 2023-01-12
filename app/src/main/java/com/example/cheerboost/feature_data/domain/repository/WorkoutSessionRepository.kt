package com.example.cheerboost.feature_data.domain.repository

import com.example.cheerboost.feature_data.domain.model.WorkoutSession
import kotlinx.coroutines.flow.Flow

interface WorkoutSessionRepository {
    fun getWorkoutSessions(): Flow<List<WorkoutSession>>
    suspend fun getWorkoutSessionById(id: Int): WorkoutSession?
    suspend fun insertWorkoutSession(session: WorkoutSession)
    suspend fun deleteWorkoutSession(session: WorkoutSession)
}
