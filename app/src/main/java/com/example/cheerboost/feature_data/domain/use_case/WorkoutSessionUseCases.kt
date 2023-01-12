package com.example.cheerboost.feature_data.domain.use_case

data class WorkoutSessionUseCases(
    val getWorkoutSessions: GetWorkoutSessions,
    val getWorkoutSession: GetWorkoutSession,
    val insertWorkoutSession: InsertWorkoutSession,
    val deleteWorkoutSession: DeleteWorkoutSession
)
