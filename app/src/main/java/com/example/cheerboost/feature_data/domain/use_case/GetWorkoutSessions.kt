package com.example.cheerboost.feature_data.domain.use_case

import com.example.cheerboost.feature_data.domain.model.WorkoutSession
import com.example.cheerboost.feature_data.domain.repository.WorkoutSessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWorkoutSessions(
    private val repository: WorkoutSessionRepository
) {
    operator fun invoke(): Flow<List<WorkoutSession>> {
        return repository.getWorkoutSessions().map { sessions ->
            sessions.sortedByDescending { it.dateTime }
        }
    }
}
