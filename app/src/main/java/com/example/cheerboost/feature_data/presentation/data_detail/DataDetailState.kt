package com.example.cheerboost.feature_data.presentation.data_detail

import java.time.LocalDateTime

data class DataDetailState(
    val workoutType: String = "",
    val count: Int = 0,
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val id: Int? = null
)
