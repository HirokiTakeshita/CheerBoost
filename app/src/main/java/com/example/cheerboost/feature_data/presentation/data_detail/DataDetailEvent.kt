package com.example.cheerboost.feature_data.presentation.data_detail

sealed class DataDetailEvent {
    data class SaveSession(val count: Int) : DataDetailEvent()
    object DeleteSession : DataDetailEvent()
}
