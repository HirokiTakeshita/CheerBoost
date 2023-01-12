
package com.example.cheerboost.feature_count.presentation.count

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheerboost.core.util.WorkoutType
import com.example.cheerboost.feature_count.domain.sensor.MeasurableSensor
import com.example.cheerboost.feature_count.domain.voice.Avatar
import com.example.cheerboost.feature_count.domain.voice.VoiceEvent
import com.example.cheerboost.feature_setting.domain.use_case.SettingUseCases
import com.example.cheerboost.feature_setting.presentation.settings.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class CountViewModel @Inject constructor(
    @Named("Gravity") private val gravitySensor: MeasurableSensor,
    @Named("LinearAcceleration") private val linearAccelerationSensor: MeasurableSensor,
    private val avatar: Avatar,
    private val settingUseCases: SettingUseCases
) : ViewModel() {
    private val _state = mutableStateOf(CountState())
    val state: State<CountState> = _state

    private var currentAvatar: String = SettingsState().avatarType
    private var isCurrentCountOnlyMode: Boolean = SettingsState().isCountOnlyMode

    private var gValueY by mutableStateOf(0f)
    private var lAccValueX by mutableStateOf(0f)
    private var lAccValueY by mutableStateOf(0f)
    private var lAccValueZ by mutableStateOf(0f)
    private var isCurrentStartPosition by mutableStateOf(false)
    private var isCurrentEndPosition by mutableStateOf(false)
    private var isBodyOnceStartPosition by mutableStateOf(false)
    private var isCurrentSpeedGood by mutableStateOf(false)

    init {
        viewModelScope.launch {
            settingUseCases.getSetting()?.let { setting ->
                currentAvatar = setting.avatarType
                isCurrentCountOnlyMode = setting.isCountOnlyMode
            }
        }
    }

    fun onEvent(event: CountEvent) {
        when (event) {
            is CountEvent.OnStartOrPauseButtonClick -> {
                _state.value = state.value.copy(
                    isSessionStart = !state.value.isSessionStart
                )
                if (state.value.isSessionStart) startCounting(event.workoutType) else stopCounting()
            }
        }
    }

    private fun startCounting(workoutType: String) {
        when (workoutType) {
            WorkoutType.SIT_UP -> countSitUp()
            WorkoutType.SQUAT -> countSquat()
            WorkoutType.DUMBBELL_PRESS -> countDumbbellPress()
        }
    }

    private fun stopCounting() {
        gravitySensor.stopListening()
        linearAccelerationSensor.stopListening()
    }

    private fun countSitUp() {
        gravitySensor.startListening()
        linearAccelerationSensor.startListening()
        gravitySensor.setOnSensorValuesChangedListener { values ->
            gValueY = values[1]
            isCurrentStartPosition = gValueY < 4
            isCurrentEndPosition = gValueY > 9.5
            if (isCurrentStartPosition) isBodyOnceStartPosition = true
            if (isBodyOnceStartPosition && isCurrentEndPosition) {
                _state.value = state.value.copy(
                    count = state.value.count + 1
                )
                viewModelScope.launch {
                    avatar.playVoice(currentAvatar, VoiceEvent.Count(state.value.count))
                }
                isBodyOnceStartPosition = false
            }
        }
        linearAccelerationSensor.setOnSensorValuesChangedListener { values ->
            if (isCurrentCountOnlyMode) return@setOnSensorValuesChangedListener
            lAccValueY = values[1]
            lAccValueZ = values[2]
            isCurrentSpeedGood = (8 < gValueY && gValueY < 9) &&
                (lAccValueY > 0.1 && lAccValueZ < -0.1)
            if (isCurrentSpeedGood) {
                viewModelScope.launch {
                    avatar.playVoice(currentAvatar, VoiceEvent.Encourage)
                }
            }
        }
    }

    private fun countSquat() {
        gravitySensor.startListening()
        linearAccelerationSensor.startListening()
        gravitySensor.setOnSensorValuesChangedListener { values ->
            gValueY = values[1]
            isCurrentStartPosition = gValueY > 8.6
            isCurrentEndPosition = gValueY <= 8.6
            if (isCurrentStartPosition) isBodyOnceStartPosition = true
            if (isBodyOnceStartPosition && isCurrentEndPosition) {
                _state.value = state.value.copy(
                    count = state.value.count + 1
                )
                viewModelScope.launch {
                    avatar.playVoice(currentAvatar, VoiceEvent.Count(state.value.count))
                }
                isBodyOnceStartPosition = false
            }
        }
        linearAccelerationSensor.setOnSensorValuesChangedListener { values ->
            if (isCurrentCountOnlyMode) return@setOnSensorValuesChangedListener
            lAccValueY = values[1]
            isCurrentSpeedGood = lAccValueY > 4
            if (isBodyOnceStartPosition && isCurrentSpeedGood) {
                viewModelScope.launch {
                    avatar.playVoice(currentAvatar, VoiceEvent.Encourage)
                }
            }
        }
    }

    private fun countDumbbellPress() {
        gravitySensor.startListening()
        linearAccelerationSensor.startListening()
        gravitySensor.setOnSensorValuesChangedListener { values ->
            gValueY = values[1]
            isCurrentStartPosition = 0.5 < gValueY && gValueY < 2.5
            if (isCurrentStartPosition) isBodyOnceStartPosition = true
        }
        linearAccelerationSensor.setOnSensorValuesChangedListener { values ->
            lAccValueX = values[0]
            isCurrentSpeedGood = lAccValueX > 2.5
            if (isBodyOnceStartPosition && isCurrentSpeedGood) {
                _state.value = state.value.copy(
                    count = state.value.count + 1
                )
                viewModelScope.launch {
                    avatar.playVoice(currentAvatar, VoiceEvent.Count(state.value.count))
                    if (!isCurrentCountOnlyMode) {
                        delay(300L)
                        avatar.playVoice(currentAvatar, VoiceEvent.Encourage)
                    }
                }
                isBodyOnceStartPosition = false
            }
        }
    }
}
