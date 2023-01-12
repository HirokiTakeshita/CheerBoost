package com.example.cheerboost.feature_setting.presentation.settings

import com.example.cheerboost.core.util.VoiceAvatarType

data class SettingsState(
    val avatarType: String = VoiceAvatarType.YOUNG_MAN,
    val isCountOnlyMode: Boolean = false
)
