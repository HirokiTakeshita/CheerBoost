package com.example.cheerboost.feature_count.presentation.count

sealed class CountEvent {
    data class OnStartOrPauseButtonClick(val workoutType: String) : CountEvent()
}
