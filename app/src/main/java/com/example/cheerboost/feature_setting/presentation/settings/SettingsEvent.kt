package com.example.cheerboost.feature_setting.presentation.settings

sealed class SettingsEvent {
    data class SelectAvatar(val avatarType: String) : SettingsEvent()
    object ToggleCountOnlyMode : SettingsEvent()
}
