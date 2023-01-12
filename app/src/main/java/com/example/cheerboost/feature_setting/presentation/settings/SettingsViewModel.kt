package com.example.cheerboost.feature_setting.presentation.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheerboost.feature_setting.domain.model.Setting
import com.example.cheerboost.feature_setting.domain.use_case.SettingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingUseCases: SettingUseCases
) : ViewModel() {
    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    private var getSettingJob: Job? = null

    init {
        getSetting()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SelectAvatar -> {
                val isSelectedAvatarCurrentlySet = state.value.avatarType == event.avatarType
                if (isSelectedAvatarCurrentlySet) return
                _state.value = state.value.copy(
                    avatarType = event.avatarType
                )
                insertSetting(state)
            }
            is SettingsEvent.ToggleCountOnlyMode -> {
                _state.value = state.value.copy(
                    isCountOnlyMode = !state.value.isCountOnlyMode
                )
                insertSetting(state)
            }
        }
    }

    private fun getSetting() {
        getSettingJob?.cancel()
        getSettingJob = viewModelScope.launch {
            settingUseCases.getSetting()?.let { setting ->
                _state.value = state.value.copy(
                    avatarType = setting.avatarType,
                    isCountOnlyMode = setting.isCountOnlyMode
                )
            }
        }
    }

    private fun insertSetting(state: State<SettingsState>) {
        viewModelScope.launch {
            settingUseCases.insertSetting(
                Setting(
                    avatarType = state.value.avatarType,
                    isCountOnlyMode = state.value.isCountOnlyMode,
                    id = Setting.settingId
                )
            )
        }
    }
}
