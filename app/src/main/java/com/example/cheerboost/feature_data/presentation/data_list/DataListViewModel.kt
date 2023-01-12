package com.example.cheerboost.feature_data.presentation.data_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheerboost.feature_data.domain.use_case.WorkoutSessionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class DataListViewModel @Inject constructor(
    private val workoutSessionUseCases: WorkoutSessionUseCases
) : ViewModel() {
    private val _state = mutableStateOf(DataListState())
    val state: State<DataListState> = _state

    private var getSessionsJob: Job? = null

    init {
        getSessions()
    }

    private fun getSessions() {
        getSessionsJob?.cancel()
        getSessionsJob = workoutSessionUseCases.getWorkoutSessions().onEach { sessions ->
            _state.value = state.value.copy(
                sessions = sessions
            )
        }.launchIn(viewModelScope)
    }
}
