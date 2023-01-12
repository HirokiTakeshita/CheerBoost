package com.example.cheerboost.feature_data.domain.use_case

import com.example.cheerboost.feature_data.domain.model.WorkoutSession
import com.example.cheerboost.feature_data.domain.repository.WorkoutSessionRepository

class InsertWorkoutSession(
    private val repository: WorkoutSessionRepository
) {
    suspend operator fun invoke(session: WorkoutSession) {
        repository.insertWorkoutSession(session)
    }
}
