package com.example.cheerboost.feature_data.data.repository

import com.example.cheerboost.feature_data.data.data_source.WorkoutSessionDao
import com.example.cheerboost.feature_data.domain.model.WorkoutSession
import com.example.cheerboost.feature_data.domain.repository.WorkoutSessionRepository
import kotlinx.coroutines.flow.Flow

class WorkoutSessionRepositoryImpl(
    private val dao: WorkoutSessionDao
) : WorkoutSessionRepository {
    override fun getWorkoutSessions(): Flow<List<WorkoutSession>> {
        return dao.getWorkoutSessions()
    }

    override suspend fun getWorkoutSessionById(id: Int): WorkoutSession? {
        return dao.getWorkoutSessionById(id)
    }

    override suspend fun insertWorkoutSession(session: WorkoutSession) {
        dao.insertWorkoutSession(session)
    }

    override suspend fun deleteWorkoutSession(session: WorkoutSession) {
        dao.deleteWorkoutSession(session)
    }
}
