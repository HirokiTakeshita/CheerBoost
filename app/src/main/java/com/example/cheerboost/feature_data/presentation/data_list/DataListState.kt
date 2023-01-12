package com.example.cheerboost.feature_data.presentation.data_list

import com.example.cheerboost.feature_data.domain.model.WorkoutSession

data class DataListState(
    val sessions: List<WorkoutSession> = emptyList()
)
