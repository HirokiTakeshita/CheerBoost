package com.example.cheerboost.feature_data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class WorkoutSession(
    val workoutType: String,
    val count: Int,
    val dateTime: LocalDateTime,
    @PrimaryKey val id: Int? = null
)
