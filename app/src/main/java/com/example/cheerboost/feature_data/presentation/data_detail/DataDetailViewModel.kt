package com.example.cheerboost.feature_data.presentation.data_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheerboost.core.util.UiEvent
import com.example.cheerboost.feature_data.domain.model.WorkoutSession
import com.example.cheerboost.feature_data.domain.use_case.WorkoutSessionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DataDetailViewModel @Inject constructor(
    private val workoutSessionUseCases: WorkoutSessionUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(DataDetailState())
    val state: State<DataDetailState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var session: WorkoutSession? = null

    init {
        savedStateHandle.get<Int>("sessionId")?.let { sessionId ->
            if (sessionId == -1) {
                savedStateHandle.get<String>("workoutType")?.let { workoutType ->
                    _state.value = state.value.copy(
                        workoutType = workoutType
                    )
                }
                savedStateHandle.get<Int>("count")?.let { count ->
                    _state.value = state.value.copy(
                        count = count
                    )
                }
            } else {
                viewModelScope.launch {
                    session = workoutSessionUseCases.getWorkoutSession(sessionId)?.also { session ->
                        _state.value = state.value.copy(
                            workoutType = session.workoutType,
                            count = session.count,
                            dateTime = session.dateTime,
                            id = session.id
                        )
                    }
                }
                savedStateHandle.get<Int>("count")?.let { count ->
                    _state.value = state.value.copy(
                        count = count
                    )
                }
            }
        }
    }

    fun onEvent(event: DataDetailEvent) {
        when (event) {
            is DataDetailEvent.SaveSession -> {
                viewModelScope.launch {
                    workoutSessionUseCases.insertWorkoutSession(
                        WorkoutSession(
                            workoutType = state.value.workoutType,
                            count = event.count,
                            dateTime = state.value.dateTime,
                            id = state.value.id
                        )
                    )
                    _eventFlow.emit(UiEvent.Navigate)
                }
            }
            is DataDetailEvent.DeleteSession -> {
                viewModelScope.launch {
                    session?.let {
                        workoutSessionUseCases.deleteWorkoutSession(it)
                    }
                    _eventFlow.emit(UiEvent.Navigate)
                }
            }
        }
    }
}
