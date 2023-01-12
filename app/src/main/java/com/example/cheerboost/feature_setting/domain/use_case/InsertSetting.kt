package com.example.cheerboost.feature_setting.domain.use_case

import com.example.cheerboost.feature_setting.domain.model.Setting
import com.example.cheerboost.feature_setting.domain.repository.SettingRepository

class InsertSetting(
    private val repository: SettingRepository
) {
    suspend operator fun invoke(setting: Setting) {
        repository.insertSetting(setting)
    }
}
